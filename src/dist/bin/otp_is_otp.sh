#!/bin/sh
#
# a simple utility to see if a set of characters will pass the test for a valid OTP
#
# It will prompt you for a PIN + Token Code, and return true or false
#
java -Djava.library.path=/usr/lib64/ldapotp -classpath ".:/usr/share/java/JLdapOtpInterface/*"  gov.llnl.lc.stg.ldapotp.test.OTP_is_otp
