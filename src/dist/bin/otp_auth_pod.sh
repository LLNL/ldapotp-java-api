#!/bin/sh
#
# a simple utility to retrieve a persons Password of the Day, or POD
#
# It will prompt you for an OUN and a PIN + Token Code, and returns the complete results
#  of the authentication, which includes a POD, if successfully authenticated 
#
java -Djava.library.path=/usr/lib64/ldapotp -classpath ".:/usr/share/java/JLdapOtpInterface/*"  gov.llnl.lc.stg.ldapotp.test.OTP_AuthenticatePOD
