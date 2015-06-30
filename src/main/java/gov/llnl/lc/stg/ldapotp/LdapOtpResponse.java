/************************************************************
 * Copyright (c) 2015, Lawrence Livermore National Security, LLC.
 * Produced at the Lawrence Livermore National Laboratory.
 * Written by Timothy Meier, meier3@llnl.gov, All rights reserved.
 * LLNL-CODE-673346
 *
 * This file is part of the OpenSM Monitoring Service (OMS) package.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (as published by
 * the Free Software Foundation) version 2.1 dated February 1999.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * OUR NOTICE AND TERMS AND CONDITIONS OF THE GNU GENERAL PUBLIC LICENSE
 *
 * Our Preamble Notice
 *
 * A. This notice is required to be provided under our contract with the U.S.
 * Department of Energy (DOE). This work was produced at the Lawrence Livermore
 * National Laboratory under Contract No.  DE-AC52-07NA27344 with the DOE.
 *
 * B. Neither the United States Government nor Lawrence Livermore National
 * Security, LLC nor any of their employees, makes any warranty, express or
 * implied, or assumes any liability or responsibility for the accuracy,
 * completeness, or usefulness of any information, apparatus, product, or
 * process disclosed, or represents that its use would not infringe privately-
 * owned rights.
 *
 * C. Also, reference herein to any specific commercial products, process, or
 * services by trade name, trademark, manufacturer or otherwise does not
 * necessarily constitute or imply its endorsement, recommendation, or favoring
 * by the United States Government or Lawrence Livermore National Security,
 * LLC. The views and opinions of authors expressed herein do not necessarily
 * state or reflect those of the United States Government or Lawrence Livermore
 * National Security, LLC, and shall not be used for advertising or product
 * endorsement purposes.
 *
 *        file: LdapOtpResponse.java
 *
 *  Created on: June 29, 2015
 *      Author: meier3
 ********************************************************************/

package gov.llnl.lc.stg.ldapotp;

/**********************************************************************
 * An LdapOtpResponse contains the results of an authentication attempt.
 * <p>
 * @author meier3
 * 
 * @version Jun 30, 2015 10:43:33 AM
 **********************************************************************/
public class LdapOtpResponse
{
  /**  authenticated indicates the success/fail state of an authentication attempt **/
private boolean authenticated;
  /**  returnCode is a value that respresents the results of an authentication attempt, including error codes **/
private int returnCode;
  /**  A password of the day, if authentication was successful, and if a Pod was requested **/
private String Pod;
  /**  if authentication failed, this may contain a descriptive message **/
private String ErrorMsg;

/************************************************************
 * Method Name:
 *  isAuthenticated
**/
/**
 * A simple boolean representing if the response indicates if
 * the authentication attempt succeeded or failed.
 *
 * @return  t/f
 ***********************************************************/
public boolean isAuthenticated()
  {
    return authenticated;
  }

/************************************************************
 * Method Name:
 *  getReturnCode
**/
/**
 * Returns the resultant code from an authentication attempt.  This
 * will be zero for success, or a negative value for failures.
 *
 * @return  the result value from an authentication attempt.
 ***********************************************************/
public int getReturnCode()
  {
    return returnCode;
  }

/************************************************************
 * Method Name:
 *  getPod
**/
/**
 * Gets the password of the day, if authentication succeeded and if it
 * was requested.
 *
 * @return  the password of the day
 ***********************************************************/
public String getPod()
  {
    return Pod;
  }

  /************************************************************
 * Method Name:
 *  getErrorMsg
**/
/**
 * If authentication fails, this may describe the mode of failure.
 *
 * @return  a message describing the failure
 ***********************************************************/
public String getErrorMsg()
  {
    return ErrorMsg;
  }

  /************************************************************
 * Method Name:
 *  LdapOtpResponse
**/
/**
 * Normally this constructor is called from within the native layer, and not
 * directly invoked.  The result of an authentication attempt.
 *
 * @see     LdapOtpInterface#OTP_Authenticate(String, String, boolean)
 *
 * @param authenticated a simple boolean indicating if authentication succeeded
 * @param returnCode    an int containing the result value, with possible error values
 * @param pod           a kerberos password of the day, coorsponding to a successful authentication
 * @param errorMsg      a descriptive message if authentication fails
 ***********************************************************/
public LdapOtpResponse(boolean authenticated, int returnCode, String pod, String errorMsg)
  {
    super();
    this.authenticated = authenticated;
    this.returnCode = returnCode;
    Pod = pod;
    ErrorMsg = errorMsg;
  }

  @Override
  public String toString()
  {
    return "LdapOtpResponse [authenticated=" + authenticated + ", returnCode=" + returnCode
        + ", Pod=" + Pod + ", ErrorMsg=" + ErrorMsg + "]";
  }

}
