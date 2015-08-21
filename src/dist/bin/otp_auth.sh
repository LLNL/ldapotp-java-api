#!/bin/sh
#
# a simple utility to see if a users official name (UON) and OTP (PIN + Token Code) will authenticate
#
# It will prompt you for an OUN and a PIN + Token Code, and returns success or denied
#
java -Djava.library.path=/usr/lib64/ldapotp -classpath ".:/usr/share/java/JLdapOtpInterface/*"  gov.llnl.lc.stg.ldapotp.test.OTP_Authenticate
