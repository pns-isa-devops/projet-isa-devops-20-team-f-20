#!/usr/bin/env bash

case "$(uname -s)" in

   CYGWIN*|MINGW*|MSYS*)
     csc -out:server.exe -recurse:*.cs;;


   *)
     mcs src/*.cs -pkg:wcf -out:server.exe;;

esac
