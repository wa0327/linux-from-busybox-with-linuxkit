#!/bin/sh
cd $(dirname $0)

version=`cat KERNEL_VERSION`
kernel="kernel-stuff/$version/vmlinuz"
initrd=initrd.gz
args=(
  -A -u -U 7523f2ed-406e-49f0-9fcb-6bd7893db409
  -c 2
  -m 1G
  -l com1,stdio
  -s 0,hostbridge
  -s 31,lpc
  -s 2,virtio-net
  -s 7,virtio-rnd
  -s 4,ahci-hd,system.part
  -f kexec,$kernel,$initrd,"console=ttyS0 printk.time=1 loglevel=5"
)
exec sudo hyperkit2 "${args[@]}"
