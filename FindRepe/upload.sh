#!/bin/sh

if [ $# -lt 3 ] ; then
  echo upload.sh mode version summary
  echo mode 0=normal 1=proguard 2=zip
  exit
fi

filename=findrepe-"$2"
filenamezip="$filename".zip
mode=$1

if [ -d $filename ] ; then
  rm -r $filename
fi

if [ -f $filenamezip ] ; then
  rm $filenamezip
fi

cp GPLv3.txt $filename/


if [ $1 -eq 1 ] ; then
  echo "mode proguard"
  proguard @findrepe.proguard
  cp -R FindRepe.jar $filename/
  cp -R GPLv3.txt $filename/
  zip $filenamezip $filename/FindRepe.jar $filename/GPLv3.txt
elif [ $1 -eq 2 ] ; then
  echo "mode zip"
  cp -R dist $filename/
  cp -R GPLv3.txt $filename/
  zip $filenamezip \
      $filename/FindRepe.jar \
      $filename/lib/cafe.jar \
      $filename/lib/commons-compress-1.0.jar \
      $filename/GPLv3.txt
else
  echo "mode normal"
  cp -R dist $filename/
  cp -R GPLv3.txt $filename/
  zip $filenamezip \
      $filename/FindRepe.jar \
      $filename/lib/cafe.jar \
      $filename/lib/commons-compress-1.0.jar \
      $filename/GPLv3.txt
fi

rm -r $filename

if [ $1 -eq 2 ] ; then
  exit
fi

./googlecode_upload.py \
	-s "$3" \
	-p softenido \
	-l Featured,Type-Archive,OpSys-All \
	$filenamezip
