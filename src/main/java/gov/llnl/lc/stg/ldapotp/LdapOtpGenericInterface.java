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
 *        file: LdapOtpGenericInterface.java
 *
 *  Created on: June 29, 2015
 *      Author: meier3
 ********************************************************************/

package gov.llnl.lc.stg.ldapotp;

/**********************************************************************
 * The LdapOtpGenericInterface provides a default implementation of the
 * LdapOtpInterface, from which all other interfaces should be extended,
 * and override selected methods within this class.
 * <p>
 * @see  LdapOtpInterface
 *
 * @author meier3
 * 
 * @version Jun 30, 2015 2:06:38 PM
 **********************************************************************/
public class LdapOtpGenericInterface implements LdapOtpInterface
{
  /** boolean specifying whether the interface has been loaded **/
  protected static boolean IsLoaded = false;

  /** the name of the Interface **/
  private static String InterfaceName = "LdapOtpGenericInterface";

  /** boolean specifying whether the interface is initialized **/
  private static boolean IsInitialized = false;


  @Override
  public boolean OTP_is_otp(String passcode)
  {
    return false;
  }

  @Override
  public boolean OTP_Authenticate(String username, String passcode)
  {
    return false;
  }

  @Override
  public LdapOtpResponse OTP_Authenticate(String username, String passcode, boolean returnPOD)
  {
    return new LdapOtpResponse(false, 0, null, null);
  }
  

  /**************************************************************************
  *** Method Name:
  ***     isInterfaceAvailable
  **/
  /**
  *** True only if methods in the concrete class can be invoked without causing a
  *** runtime or link error.  In general, this method is provided to make sure the
  *** native DLL was loaded prior to invoking functions.
  *** <p>
  ***
  *** @return       true if methods in the interface can be called, and false if not.
  **************************************************************************/

  public boolean isInterfaceAvailable()
  {
    return IsLoaded;
  }
  
  /**************************************************************************
  *** Method Name:
  ***     initialize
  **/
  /**
  *** Performs all necessary resource allocation, configuration, and initialization.
  *** <p>
  ***
  *** @return       true if initialization was successful
  **************************************************************************/

  public boolean initialize()
  {
    IsInitialized = true;
    return IsInitialized;
  }

  /**************************************************************************
  *** Method Name:
  ***     destroy
  **/
  /**
  *** Can be considered a finalizer routine, that releases resources allocated
  *** by the initialize method.
  *** <p>
  ***
  *** @see          #initialize
  ***
  *** @return       true if all resources were released
  **************************************************************************/

  public boolean destroy()
  {
    IsInitialized = false;
    return !IsInitialized;
  }
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
    return InterfaceName;
  }

}
