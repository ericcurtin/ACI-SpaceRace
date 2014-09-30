/*******************************************************************************
 * Copyright (c) 2001-2014 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 *******************************************************************************/
package net.sf.robocode.repository.root;


import net.sf.robocode.io.FileUtil;
import net.sf.robocode.io.Logger;
import net.sf.robocode.io.URLJarCollector;
import net.sf.robocode.io.JarJar;
import net.sf.robocode.repository.IRepository;
import net.sf.robocode.repository.packager.JarExtractor;
import net.sf.robocode.repository.items.IRepositoryItem;
import net.sf.robocode.repository.items.RobotItem;
import net.sf.robocode.repository.items.handlers.ItemHandler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;


/**
 * Represents a JAR file.
 *
 * @author Pavel Savara (original)
 * @author Flemming N. Larsen (contributor)
 */
public final class JarRoot extends BaseRoot implements IRepositoryRoot {
	private static final long serialVersionUID = 1L;

	private final String jarPath; // without a separator ("/!")
	private final URL jarUrl;

	private long lastModified;

	public JarRoot(IRepository repository, File rootPath) {
		super(repository, rootPath);
		String jarPath = null;
		URL jarUrl = null;
		try {
			jarPath = "jar:" + rootPath.toURI().toString();
			jarUrl = new URL(jarPath + "!/");
		} catch (MalformedURLException e) {
			Logger.logError(e);
		}
		this.jarPath = jarPath;
		this.jarUrl = jarUrl;
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateItems(boolean force) {
		setStatus("Updating JAR: " + rootPath.toString());

		long lastModified = rootPath.lastModified();

		if (lastModified > this.lastModified) {
			repository.removeItemsFromRoot(this);
			this.lastModified = lastModified;

			List<IRepositoryItem> repositoryItems = new ArrayList<IRepositoryItem>();

			visitItems(repositoryItems);
			for (IRepositoryItem repositoryItem : repositoryItems) {
				repositoryItem.update(lastModified, force);
			}
		}
	}

	private void visitItems(List<IRepositoryItem> repositoryItems) {
		String root = jarPath;
		InputStream is = null;
		BufferedInputStream bis = null;
		JarInputStream jarIS = null;

		try {
			URLConnection con = URLJarCollector.openConnection(rootURL);

			is = con.getInputStream();
			bis = new BufferedInputStream(is);
			jarIS = new JarInputStream(bis);
			readJarStream(repositoryItems, root, jarIS);

		} catch (Exception e) {
			Logger.logError(rootURL + " is probably corrupted (" + e.getClass().getName() + " " + e.getMessage() + ")");
		} finally {
			FileUtil.cleanupStream(jarIS);
			FileUtil.cleanupStream(bis);
			FileUtil.cleanupStream(is);
		}
	}

	private void readJarStream(List<IRepositoryItem> repositoryItems, String root, JarInputStream jarIS) throws IOException {
		JarEntry entry = jarIS.getNextJarEntry();

		while (entry != null) {
			String name = entry.getName().toLowerCase();

			if (!entry.isDirectory()) {
				if (name.contains(".data/") && !name.contains(".robotcache/")) {
					JarExtractor.extractFile(FileUtil.getRobotsDataDir(), jarIS, entry);
				} else {
					if (name.endsWith(".jar") || name.endsWith(".zip")) {
						JarInputStream inner = null;

						try {
							inner = new JarInputStream(jarIS);
							readJarStream(repositoryItems, "jar:jar" + root + JarJar.SEPARATOR + entry.getName(), inner);
						} finally {
							if (inner != null) {
								inner.closeEntry();								
							}
						}
					} else {
						createItem(repositoryItems, new URL(root + "!/"), entry);
					}
				}
			}
			entry = jarIS.getNextJarEntry();
		}
	}

	private void createItem(List<IRepositoryItem> repositoryItems, URL root, JarEntry entry) {
		try {
			String pUrl = root.toString() + entry.getName();
			IRepositoryItem repositoryItem = ItemHandler.registerItem(new URL(pUrl), JarRoot.this, repository);

			if (repositoryItem != null) {
				if (repositoryItem instanceof RobotItem) {
					RobotItem robotItem = (RobotItem) repositoryItem; 

					robotItem.setClassPathURL(root);
				}
				repositoryItems.add(repositoryItem);
			}
		} catch (MalformedURLException e) {
			Logger.logError(e);
		}
	}

	public void updateItem(IRepositoryItem repositoryItem, boolean force) {
		repositoryItem.update(rootPath.lastModified(), force);
	}

	public boolean isChanged(IRepositoryItem repositoryItem) {
		return rootPath.lastModified() > lastModified;
	}

	public URL getURL() {
		return jarUrl;
	}

	public boolean isDevelopmentRoot() {
		return false;
	}

	public boolean isJAR() {
		return true;
	}

	public void extractJAR() {
		JarExtractor.extractJar(rootURL);
	}
}
