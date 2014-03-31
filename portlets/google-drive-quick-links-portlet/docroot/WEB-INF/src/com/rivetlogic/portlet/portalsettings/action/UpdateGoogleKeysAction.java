package com.rivetlogic.portlet.portalsettings.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.ParamUtil;

public class UpdateGoogleKeysAction extends BaseStrutsPortletAction {
	
	public void processAction(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest request,
			ActionResponse response) throws Exception {
		
		String clientId = ParamUtil.getString(request, "clientId");
		String developerKey = ParamUtil.getString(request, "developerKey");
		
		System.out.println(clientId);
		System.out.println(developerKey);
		System.out.println("Entre");
		
		//developerKey=AIzaSyCxu6VgWsk-u0ZpFgGhca9cdXCa8jyVxXo
		//clientId=860446398446-sihdkn46rv9q6c0d7naf4pir1lmmu2tt.apps.googleusercontent.com
		
		PortletPreferences prefs = request.getPreferences();
		prefs.setValue("clientId",  clientId);
		prefs.setValue("developerKey",  developerKey);
		prefs.store();
		
		originalStrutsPortletAction.processAction(originalStrutsPortletAction,
				portletConfig, request, response);
	}
	
	public String render(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest request,
			RenderResponse response) throws Exception {
	   return originalStrutsPortletAction.render(null, portletConfig,
				request, response);
   }

   public void serveResource(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws Exception {
		originalStrutsPortletAction.serveResource(originalStrutsPortletAction,
				portletConfig, resourceRequest, resourceResponse);
   }
}
