#!/bin/sh
cd $(dirname $0)

vmdata="$HOME/Library/Containers/com.docker.docker/Data"
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
  -s 2,ahci-hd,system.part
  -s 7,virtio-rnd  
)

[[ $1 == 'uefi' ]] && {
  args+=(
    -s 1,virtio-vpnkit,"path=$vmdata/vpnkit.eth.sock,uuid=73f0acc7-1a10-4630-a977-5d9266b192e2"
    -s 3,ahci-hd,boot.part
    -f bootrom,../UEFI/UEFI.fd #有 kernel 版本限制，目前測過的: 4.9.x
  )
} || {
  args+=(
    -s 1,virtio-net
    -f kexec,$kernel,$initrd,"console=ttyS0 printk.time=1 loglevel=5"
  )
}

exec sudo hyperkit2 "${args[@]}"
