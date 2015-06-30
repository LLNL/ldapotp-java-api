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
 *        file: OTP_AuthenticatePOD.java
 *
 *  Created on: June 29, 2015
 *      Author: meier3
 ********************************************************************/
package gov.llnl.lc.stg.ldapotp.test;

import gov.llnl.lc.logging.CommonLogger;
import gov.llnl.lc.stg.ldapotp.LdapOtpInterface;
import gov.llnl.lc.stg.ldapotp.LdapOtpResponse;

import java.io.Console;
import java.util.Arrays;

/**
 * Command line tool that attempts to authenticate a user with a
 * "one-time-password" (OTP), and if successful, returns a "password
 * of the day" (POD).
 * 
 * @author meier3
 *
 */
public class OTP_AuthenticatePOD implements CommonLogger
{
  /**
   * The default method for this class attempts to authenticate a user.
   * It prompts for an Official User Name (OUN), and then a passcode
   * (PIN + Token Code), and then uses the interface to perform the
   * authentication.
   * 
   * If authentication is successful, the response will contain the
   * "password of the day" or POD.
   * 
   * @param args if an argument is supplied, the simulated LDAPTOP interface
   *             will be used, as opposed to the real java native interface
   *             (normally should not provide an argument)
   */
  public static void main(String[] args)
  {
    LdapOtpInterface otpInt = LdapOtpTestUtils.getInterface(args);
    Console con = System.console();

    try
    {
      String uname = con.readLine("Enter your OUN: ");
      char[] passcode = con.readPassword("Enter %s's passcode (PIN + Token Code): ", uname);
      LdapOtpResponse otpRsnse = otpInt.OTP_Authenticate(uname, new String(passcode), true);
      System.out.println("Authentication Response: " + otpRsnse);
      Arrays.fill(passcode, ' ');
    }
    catch (Exception e)
    {
      logger.severe(e.getMessage());
    }
  }
}
