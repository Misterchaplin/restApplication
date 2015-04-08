package net.technics;

import net.models.Questionnaire;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class ProductTvProvider implements ITableLabelProvider {

	@Override
	public void addListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub

	}

	/*@Override
	public Image getColumnImage(Object obj, int numCol) {
		if (numCol == 0) {
			return Utils.getImage(Utils.IMG_PRODUCT);
		}
		return null;
	}*/

	@Override
	public String getColumnText(Object obj, int numCol) {
		Questionnaire aProduct = (Questionnaire) obj;
		if (numCol == 0) {
			String result = null;
			switch(numCol){
	        	case 0:
	        		result = ((Questionnaire) obj).getLibelle();
	        	break;

	        	case 1:
	        		result = ((Questionnaire) obj).getLibelle();
	            break;
	        }
			return result;
		}
		return null;
	}

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
