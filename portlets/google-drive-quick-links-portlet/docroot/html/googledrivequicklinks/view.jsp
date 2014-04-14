<%--
    /*
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
--%>

<%@include file="/html/init.jsp" %>

<c:set var="developerKey" value="${developerKey}"/>
<c:set var="clientId" value="${clientId}"/>

<% 
String developerKey = (String)pageContext.getAttribute("developerKey");
String clientId = (String)pageContext.getAttribute("clientId");
String userId = themeDisplay.getRealUser().getUuid();

PortletURL iteratorURL = renderResponse.createRenderURL();
iteratorURL.setParameter("jspPage", "/html/googledrivequicklinks/view.jsp");
iteratorURL.setParameter("userId", userId);
%>

<portlet:renderURL var="backURL">
    <portlet:param name="mvcPath" value="/html/googledrivequicklinks/view.jsp" />
    <portlet:param name="userId" value="${userId}"/>
</portlet:renderURL>
<portlet:actionURL name="selectDriveFile" var="selectDriveFileUrl">
    <portlet:param name="developerKey" value="${developerKey}"/>
    <portlet:param name="clientId" value="${clientId}"/>
</portlet:actionURL>
<portlet:actionURL name="addDriveLink" var="addDriveLinkUrl">
    <portlet:param name="developerKey" value="${developerKey}"/>
    <portlet:param name="clientId" value="${clientId}"/>
</portlet:actionURL>

<liferay-ui:header backURL="${backURL}" title='view-header-message'/>
<aui:form action="${selectDriveFile}" name="form" method="POST">

<liferay-ui:search-container delta="10" iteratorURL="<%= iteratorURL %>"
    emptyResultsMessage="no-links-message">

    <liferay-ui:search-container-results
        total="<%=DriveLinksLocalServiceUtil.userDriveLinksCount(userId)%>"
        results="<%=DriveLinksLocalServiceUtil.userDriveLinks(userId,
        		searchContainer.getStart(),searchContainer.getEnd()) %>"
    />

    <liferay-ui:search-container-row modelVar="content" keyProperty="NAME"
        className="com.rivetlogic.portlet.model.DriveLinks">
 	
        <liferay-ui:search-container-column-text 
            name="file-name" value="${content.NAME}" 
            href="${content.URL}" target="_blank" />
        <liferay-ui:search-container-column-jsp 
            path="/html/googledrivequicklinks/include/driveLinks_actions.jsp" />
    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
</liferay-ui:search-container>

<aui:input name="documentUrl" id="documentUrl" type="hidden" />
<aui:input name="documentName" id="documentName" type="hidden" />
<aui:input name="documentId" id="documentId" type="hidden" />

<c:if test="${developerKey == '' || clientId == ''}">
	<liferay-ui:message key="no-keys-message" />
</c:if>

<c:if test="${signedUser}">
<aui:fieldset>
    <aui:button-row>
        <aui:button type="submit" value="select-button" 
            onClick="UtilityClass.setAction('${pns}','${selectDriveFileUrl}')" />
    </aui:button-row>
</aui:fieldset>
</c:if>
</aui:form>

<c:if test="${showDrive}">
<!-- The Google API Loader script. -->
<script type="text/javascript" src="https://apis.google.com/js/api.js"></script>
<aui:script use="picker-module">
	A.MyGooglePicker.onApiLoad('${developerKey}','${clientId}','${addDriveLinkUrl}','${pns}');
</aui:script>
</c:if>