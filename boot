#!/bin/sh
DIR=$(dirname $0)
cd $DIR

kernel=vmlinuz
initrd=initrd.gz
args=(
  -A -u -U 7523f2ed-406e-49f0-9fcb-6bd7893db409
  -c 2
  -m 1G
  -l com1,stdio
  -s 0:0,hostbridge
  -s 31,lpc
  -f kexec,$kernel,$initrd,"earlyprintk=serial console=ttyS0 printk.time=1"
)
exec sudo ../xhyve "${args[@]}"
