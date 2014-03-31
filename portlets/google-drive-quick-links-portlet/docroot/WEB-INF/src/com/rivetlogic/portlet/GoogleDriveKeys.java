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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class GoogleDriveKeys {
	private static final String GOOGLE_DRIVE_CLIENTID="google.drive.clientid";
	private static final String GOOGLE_DRIVE_DEVKEY="google.drive.devkey";
	
	public static String getClientId(long companyId) throws SystemException{
		return PrefsPropsUtil.getString(companyId, GOOGLE_DRIVE_CLIENTID, 
				GetterUtil.getString(PropsUtil.get(GOOGLE_DRIVE_CLIENTID)));
	}
	
	public static String getDevKey(long companyId) throws SystemException{
		return PrefsPropsUtil.getString(companyId, GOOGLE_DRIVE_DEVKEY, 
				GetterUtil.getString(PropsUtil.get(GOOGLE_DRIVE_DEVKEY)));
	}
}
