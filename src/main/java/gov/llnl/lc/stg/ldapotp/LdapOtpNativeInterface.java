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
 *        file: LdapOtpNativeInterface.java
 *
 *  Created on: June 29, 2015
 *      Author: meier3
 ********************************************************************/

package gov.llnl.lc.stg.ldapotp;

import gov.llnl.lc.logging.CommonLogger;

/**********************************************************************
 * The LdapOtpNativeInterface is the java class that directly connects to
 * the native C libraries that perform OTP authentication.
 * <p>
 * @author meier3
 * 
 * @version Jun 30, 2015 2:59:29 PM
 **********************************************************************/
public class LdapOtpNativeInterface extends LdapOtpGenericInterface
                                    implements CommonLogger
{
  /** the name of the Native Interface (prefix of lib, and suffix of .so **/
  private static String InterfaceName = "LdapOtpJni";

  /**************************************************************************
  ***
  *** The static initializer which attempts to load the native library.
  *** <p>
  ***
  **************************************************************************/
  static
  {
    try
    {
      System.loadLibrary(InterfaceName);
      IsLoaded = true;
    }
    catch (Exception e)
    {
      logger.severe("Could not Load the Native Library");
      logger.severe(e.getMessage());
      IsLoaded = false;
    }
    catch (java.lang.UnsatisfiedLinkError ule)
    {
      logger.severe("Could not Load the Native Library");
      logger.severe(ule.getMessage());
      IsLoaded = false;
    }
  }


  /** logger for the class **/
  private java.util.logging.Logger classLogger =
      java.util.logging.Logger.getLogger( getClass().getName() );

  /**************************************************************************
  *** Method Name:
  ***     getInterfaceName
  **/
  /**
  *** Returns the actual name of the concrete object or class that implements this
  *** interface.
  *** <p>
  ***
  *** @return       the name of the actual LdapOtpInterface
  **************************************************************************/

  public String getInterfaceName()
  {
    return System.mapLibraryName(InterfaceName);
  }

  public native boolean OTP_is_otp(String passcode);

  public native boolean OTP_Authenticate(String username, String passcode);

  public native LdapOtpResponse OTP_Authenticate(String username, String passcode, boolean returnPOD);

}
