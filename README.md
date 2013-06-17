This directory contains the component of PEOS - the Process Enactment
Operating System - which is an enactment system for the PML process
modeling language.  The source code is the result of a number of
student projects carried out at the University of Colorado, Denver,
and Santa Clara University in the USA, and research projects at Lero,
the Irish Software Engineering Research Centre.  The code is released
under the MIT license (see LICENSE file).

There are numerous subsystems in various states of maturity.  At
present, the most usable is a web-based interface using CGI.  The
source code for this is found in the following sub-directories:

- pml - the PML language compiler and analysis tools.  

  pml/pml/libpml.a is required to build the PEOS "kernel"

- os/kernel - the PEOS "kernel".  This directory contains the core
  enactment engine that computes process state based on events
  submitted by users, or detected as a result of resource state
  changes.

  os/kernl/libpeos.a is required to build the web ui.

- ui/web2 - this is the original web interface shown in most of the
  PEOS research papers.

- ui/ajax-cgi - this is a work in progress comprising a CGI program
  that recomputes process state in response to events, then returns
  the state of all process instances in XML format.  A simple ajax
  front end shows how to use these data (under construction).

To build and install these subsystems, follow these steps:

1. install the TCL libraries (see http://www.activestate.com/activetcl).
2. if libtcl.a is not installed in the normal library directory
expected by the linker on your platform (_/usr/lib/libtcl.a_ on Linux
platforms), edit the following Makefiles to set _LDFLAGS_ to include the 
directory where _libtcl.a_ can be found:

   - os/kernel/Makefile
   - ui/web2/cgi-bin/Makefile
   - ui/ajax-cgi/Makefile

3. Execute _make_ in the root directory (where this _README_ file is
found).  This will invoke _make_ recursively in _pml_, _os/kernel_,
_ui/web2_, and _ui/ajax-cgi_.  

4. Edit _ui/web2/cgi-bin/Makefile_ and/or _ui/ajax-cgi/Makefile_  to
set the installation directory for the CGI program(s), html, and
javascript files.  The default is _$(HOME)/public_html/PEOS_ which is
appropriate for most Linux installations.

5. Change directory to _ui/web2/cgi-bin/_ and/or _ui/ajax-cgi/_ and
type "make install"; this will install the CGI program(s), html,
javascript, and other configuration files needed to run the PEOS web
application.

The build has been tested on Ubuntu 12.04 with TCL v. 8.5.  It may
work on other systems as well.



