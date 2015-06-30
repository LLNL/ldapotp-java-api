JLdapOtpInterface
=========================
by Tim Meier, [meier3@llnl.gov](mailto:meier3@llnl.gov)

**JLdapOtpInterface.jar** provides OTP authentication services for java applications

Released under the GNU LGPL, `LLNL-CODE-673346`.  See the `LICENSE`
file for details.

Overview
-------------------------

This java library uses a native C library (**`libLdapOtpJni`**) to interface with LLNL's standard
LDAP OTP authentication libraries.

Installation Location
-------------------------
The standard installation is located in '/usr/share/java/JLdapOtpInterface'.  An application which
uses this library should include the JAR in its class path.

`NOTE`: this package also includes **LcCommon.jar** (/usr/share/java/JLdapOtpInterface/lib) which other
        packages may depend upon.

Test Scripts
-------------------------

A few scripts for testing the functionality of the installation are located in the `bin` sub-directory
of the installation location.  They are simple one-liners,  but also contain information about their intended use.

If you use a password of the day, or POD, the _**`otp_auth_pod.sh`**_ script is a convenient way to obtain it.