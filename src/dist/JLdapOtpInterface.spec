%define java_package_name JLdapOtpInterface

Name:           	llnl-ldapotp-clt-java
Version:        	2.00
Release:        	31%{?dist}
Summary:        	Java Client Interface for the LDAP/OTP libraries

Group:          	Development/Libraries
License:        	GPL/BSD
BuildRoot: 			%{_tmppath}/%{name}-%{version}
Source0:        	%{name}-%{version}-%{release}.tar.gz
BuildArch:      	noarch

BuildRequires:  	java-devel >= 1:1.6.0
Requires:       	java >= 1:1.6.0
Requires:       	llnl-ldapotp-clt-jni-auth-libs
BuildRequires:  	jpackage-utils
BuildRequires:  	ant >= 1.6

Prefix:				/usr/share/java

%description
This package provides the JAR library which provides LDAP/OTP authentication for java clients.
Specifically, the %{java_package_name}.jar library (and all dependent jar files) are included,
as well as test routines for each LdapOtp api call.

%package javadoc
Summary:        Javadocs for %{java_package_name}
Group:          Documentation
Requires:       jpackage-utils

%description javadoc
This package contains the API documentation for %{java_package_name}.


%prep
%setup -q

%build


%install
[ "%{buildroot}" != "/" ] && rm -rf %{buildroot}
mkdir -p $RPM_BUILD_ROOT%{_javadir}
cd $RPM_BUILD_ROOT%{_javadir}
tar -xzf %{SOURCE0}
mv $RPM_BUILD_ROOT%{_javadir}/%{name}-%{version} $RPM_BUILD_ROOT%{_javadir}/%{java_package_name}
cd $RPM_BUILD_ROOT%{_javadir}/%{java_package_name}
rm -f *.spec
ln -s %{java_package_name}-*.jar %{java_package_name}.jar

mkdir -p $RPM_BUILD_ROOT%{_javadocdir}
mv $RPM_BUILD_ROOT%{_javadir}/%{java_package_name}/docs $RPM_BUILD_ROOT%{_javadocdir}/%{java_package_name}


%clean
rm -rf $RPM_BUILD_ROOT

%files
%defattr(644,root,root,755)
%{_javadir}/*
%defattr(755,root,root,755)
%{_javadir}/%{java_package_name}/bin/*

%files javadoc
%defattr(644,root,root,755)
%{_javadocdir}/%{java_package_name}


%changelog
* Fri Aug 21 2015 Tim Meier <meier3@llnl.gov> 2.00-31
- New gradle build and package.
* Thu Nov 24 2014 Tim Meier <meier3@llnl.gov> 1.0.0-27
- New LcCommon (release 19), included in this package
* Thu Oct  9 2013 Tim Meier <meier3@llnl.gov> 1.0.0-21
- No code change, updated to use configure.ac instead of configure.in
* Fri Oct  4 2013 Tim Meier <meier3@llnl.gov> 1.0.0-19
- Fixed convenience scripts to start with magic kernel shell directive
* Fri Sep 20 2013 Tim Meier <meier3@llnl.gov> 1.0.0-17
- Minor change to the default date format in LcCommon. No change to authentications.
* Fri Aug  8 2013 Tim Meier <meier3@llnl.gov> 1.0.0-15
- Minor additions to LcCommon.jar, used by many other lc applications. No change to authentications.
* Mon Jun 24 2013 Tim Meier <meier3@llnl.gov> 1.0.0-14
- Extended and improved LcCommon.jar, used by many other lc applications. No change to authentications.
* Fri May  3 2013 Tim Meier <meier3@llnl.gov> 1.0.0-13
- includes an extended and improved version of LcCommon.jar, used by many other lc applications. No change to authentications.
