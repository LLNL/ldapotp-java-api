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
 *        file: LdapOtpTestUtils.java
 *
 *  Created on: June 29, 2015
 *      Author: meier3
 ********************************************************************/

package gov.llnl.lc.stg.ldapotp.test;

import gov.llnl.lc.logging.CommonLogger;
import gov.llnl.lc.stg.ldapotp.LdapOtpInterface;
import gov.llnl.lc.stg.ldapotp.LdapOtpNativeInterface;
import gov.llnl.lc.stg.ldapotp.LdapOtpSimulatedInterface;

/**
 * Utility class for selecting and loading one of the various implementations
 * of the LdapOtpInterface.  An instance can also be tested, to see which one
 * was loaded.
 * 
 * @author meier3
 *
 */
public class LdapOtpTestUtils implements CommonLogger
{

  /**
   * Tests to see if the LDAPOTP Native Interface was loaded, and
   * returns the status.  Displays a little helpful message if the
   * interface was not loaded.
   * 
 * @param otpInt  the interface to be tested.
 * @return  true if the native library was found and loaded
 */
static protected boolean isInterfaceLoaded(LdapOtpInterface otpInt)
  {
    boolean loaded = otpInt.isInterfaceAvailable();

    // check to see if the interface (native library) got loaded
    if (!loaded)
    {
      // no real point in going further if the interface is not available
      Object[] options = { "OK" };

      // pop up a dialog to warn, and allow user to blast through anyway
      String titleMessage = "*** LdapOtp Native Interface Not Found ***";
      String warningMessage = "LdapOtpTestAuthenticator could not locate the native library.\n"
          + "The LdapOtp native library is used for all authentication\n" + "functions.\n\n"
          + "Check to ensure that the path to ( " + otpInt.getInterfaceName() + " )\n"
          + "is in the library path, and that the library exists.\n\n"
          + "Try again after resolving this dependency issue.\n\n";

      // pop-up a dialog describing the problem
      int choice = javax.swing.JOptionPane.showOptionDialog(null, warningMessage, titleMessage,
          javax.swing.JOptionPane.DEFAULT_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE, null,
          options, options[0]);

      // never continue, don't care what is selected
      if (choice == 0)
      {
      }
    }
    return loaded;
  }

  /**
   * Returns either the real java native interface for the LDAPOTP authentication libraries,
   * or the simulated one, based on the provided arguments.
   * 
   * If 'args' is null or empty, then the actual native interface is returned, if possible.
   * Otherwise the simulated interface is returned.  The simulated interface is good for systems
   * and platforms that do not have the actual native LDAPOTP authentication libraries.
   * 
   * @param args  null for the native interface, or non-null for the simulated interface
   */
  public static LdapOtpInterface getInterface(String[] args)
  {
    LdapOtpInterface otpInt;

    // ** Create one of the various LdapOtpInterfaces based on command line args
    if (args.length == 0)
      otpInt = new LdapOtpNativeInterface();
    else
    {
      otpInt = new LdapOtpSimulatedInterface();
      logger.info("NOTE: Using " + otpInt.getInterfaceName() + " interface, not Native!");
    }

    // check to see if the interface (native library) got loaded
    if (!LdapOtpTestUtils.isInterfaceLoaded(otpInt))
      logger.severe("Interface is not loaded");
    
    return otpInt;
  }
}
