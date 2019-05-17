#!/bin/sh
args=(
    -U 7523f2ed-406e-49f0-9fcb-6bd7893db409
    -A
    -c 8
    -m 8G
    -l com1,stdio
    -s 0:0,hostbridge
    -s 31,lpc
)
exec sudo hyperkit "${args[@]}" -f kexec,vmlinuz,initrd.gz,"earlyprintk=serial console=ttyS0 printk.time=1"
