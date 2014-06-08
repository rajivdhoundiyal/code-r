package com.codereview.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.codereview.views.CodeReviewWizard;

public class AddToReviewHandler extends AbstractHandler {

	public Object execute(ExecutionEvent executionEnvironment) throws ExecutionException {

		WizardDialog wizDialog = new WizardDialog(Workbench.getInstance().getDisplay().getActiveShell(),
				new CodeReviewWizard());
		wizDialog.setDefaultImage(AbstractUIPlugin.imageDescriptorFromPlugin("com.codereview", "icons/paw-16.png")
				.createImage());

		wizDialog.open();

		return null;
	}

	@Override
	protected void fireHandlerChanged(HandlerEvent handlerEvent) {
		super.fireHandlerChanged(handlerEvent);
	}
}
