/*******************************************************************************
 * Copyright (c) 2011 Juan Ignacio Barisich.
 * 
 * This file is part of JQA (http://github.com/jbaris/jqa).
 * 
 * JQA is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of 
 * the License, or (at your option) any later version.
 * 
 * JQA is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General 
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with JQA. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package ar.com.fluxit.jqa.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.Wizard;

import ar.com.fluxit.jqa.JQAEclipsePlugin;
import ar.com.fluxit.jqa.wizard.page.RulesContextSelectionWizardPage;
import ar.com.fluxit.jqa.wizard.page.TargetProjectsSelectionWizardPage;

public class JQAWizard extends Wizard {

	private boolean newRulesContext;
	private IPath rulesContextFile;
	private IProject[] targetProjects;

	public JQAWizard() {
		setForcePreviousAndNextButtons(true);
		restoreState();
	}

	@Override
	public void addPages() {
		addPage(new RulesContextSelectionWizardPage());
		addPage(new TargetProjectsSelectionWizardPage());
	}

	@Override
	public boolean canFinish() {
		return TargetProjectsSelectionWizardPage.PAGE_NAME
				.equals(getContainer().getCurrentPage().getName())
				&& getTargetProjects() != null
				&& getTargetProjects().length > 0;
	}

	@Override
	public void dispose() {
		saveState();
		super.dispose();
	}

	public IPath getRulesContextFile() {
		return rulesContextFile;
	}

	public IProject[] getTargetProjects() {
		return targetProjects;
	}

	public boolean isNewRulesContext() {
		return newRulesContext;
	}

	@Override
	public boolean performFinish() {
		// TODO
		return false;
	}

	private void restoreState() {
		try {
			final IDialogSettings settings = JQAEclipsePlugin.getDefault()
					.getDialogSettings();
			final String rulesContextFileString = settings
					.get("rulesContextFileName");
			if (rulesContextFileString != null) {
				setRulesContextFile(Path
						.fromPortableString(rulesContextFileString));
			}
			final String[] targetProjectNames = settings
					.getArray("targetProjectNames");
			if (targetProjectNames != null) {

				IProject[] targetProjects = new IProject[targetProjectNames.length];
				for (int i = 0; i < targetProjects.length; i++) {
					targetProjects[i] = ResourcesPlugin.getWorkspace()
							.getRoot().getProject(targetProjectNames[i]);
				}
				this.targetProjects = targetProjects;
			}
			this.newRulesContext = settings.getBoolean("newRulesContext");
		} catch (Exception e) {
			JQAEclipsePlugin
					.getDefault()
					.getLog()
					.log(new Status(IStatus.ERROR, JQAEclipsePlugin.PLUGIN_ID,
							IStatus.OK, "Error while restoring state", e));
		}
	}

	private void saveState() {
		final IDialogSettings settings = JQAEclipsePlugin.getDefault()
				.getDialogSettings();
		settings.put("rulesContextFileName", getRulesContextFile()
				.toPortableString());
		final int targetProjectslength = getTargetProjects().length;
		String[] targetProjectNames = new String[targetProjectslength];
		for (int i = 0; i < targetProjectslength; i++) {
			targetProjectNames[i] = getTargetProjects()[i].getName();
		}
		settings.put("targetProjectNames", targetProjectNames);
		settings.put("newRulesContext", isNewRulesContext());
	}

	public void setNewRulesContext(boolean newRulesContext) {
		this.newRulesContext = newRulesContext;
	}

	public void setRulesContextFile(IPath rulesContextFile) {
		this.rulesContextFile = rulesContextFile;
	}

	public void setTargetProjects(IProject[] targetProjects) {
		this.targetProjects = targetProjects;
	}

}
