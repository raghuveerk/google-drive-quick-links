/**
* Copyright (C) 2005-2014 Rivet Logic Corporation.
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; version 2
* of the License.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor,
* Boston, MA 02110-1301, USA.
*/

package com.rivetlogic.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import com.rivetlogic.portlet.model.impl.DriveLinksImpl;
import com.rivetlogic.portlet.service.DriveLinksLocalServiceUtil;

public class GoogleDriveQuickLinks extends MVCPortlet {
    private static final Log LOG = 
	        LogFactoryUtil.getLog(GoogleDriveQuickLinks.class);
 
    @Override
    public void doView(RenderRequest request, RenderResponse response) 
            throws IOException, PortletException {
		
        ThemeDisplay themeDisplay = 
                (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
        String googleClientId = ""; 
        String googleDeveloperKey = "";
        
		try {
			googleClientId = GoogleDriveKeys.getClientId(themeDisplay.getCompanyId());
			googleDeveloperKey = GoogleDriveKeys.getDevKey(themeDisplay.getCompanyId());
		} catch (SystemException e) {
			LOG.error(e);
		}
		
		request.setAttribute("userId", themeDisplay.getRealUser().getUuid());
		request.setAttribute("developerKey", googleDeveloperKey);
        request.setAttribute("clientId", googleClientId);
		
        super.doView(request, response);
    }
	
    public void selectDriveFile(ActionRequest request, ActionResponse response) {
		request.setAttribute("showDrive", true);    	
	}
	
	public void addDriveLink(ActionRequest request, ActionResponse response)
            throws PortletException, IOException{
		
        String documentName = ParamUtil.getString(request, "documentName");
        String documentUrl = ParamUtil.getString(request, "documentUrl");
		
        ThemeDisplay themeDisplay = 
                (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
        DriveLinksImpl driveLink = new DriveLinksImpl();
        driveLink.setNAME(documentName);
        driveLink.setURL(documentUrl);
        driveLink.setUSER_ID(themeDisplay.getRealUser().getUuid());
		
        try {
            if(!DriveLinksLocalServiceUtil.linkExist(driveLink)){
                DriveLinksLocalServiceUtil.addDriveLinks(driveLink);
            }
        } catch (SystemException e) {
            LOG.error(e);
        }
    }

    public void deleteDriveLink(ActionRequest request, ActionResponse response)
            throws PortletException, IOException{
		
        String linkName = ParamUtil.getString(request, "linkName");
        String linkUser = ParamUtil.getString(request, "linkUser");
		
        DriveLinksImpl driveLink = new DriveLinksImpl();
        driveLink.setNAME(linkName);
        driveLink.setUSER_ID(linkUser);
		
        try {
            DriveLinksLocalServiceUtil.deleteDriveLink(driveLink);
        } catch (SystemException e) { 
            LOG.error(e);
        }
    }
}
