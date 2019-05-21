#!/bin/sh
cd $(dirname $0)

vmdata="$HOME/Library/Containers/com.docker.docker/Data"
version=`cat KERNEL_VERSION`
kernel="kernel-stuff/$version/vmlinuz"
initrd=initrd.gz
args=(
  -A -u -U 940af71f-49be-4859-a50a-36d597f5f00a
  -c 2
  -m 1G
  -l com1,stdio
  -s 0,hostbridge
  -s 31,lpc
  -s 2,ahci-hd,system.disk
  -s 7,virtio-rnd
)

[[ $1 == 'uefi' ]] && {
  #有 kernel 版本限制，目前測通過的有: 4.9.x。
  args+=(
    -s 1,virtio-vpnkit,"path=$vmdata/vpnkit.eth.sock,uuid=73f0acc7-1a10-4630-a977-5d9266b192e2"
    -f bootrom,/Applications/Docker.app/Contents/Resources/uefi/UEFI.fd
  )
} || {
  args+=(
    -s 1,virtio-net
    -f kexec,$kernel,$initrd,"console=ttyS0 loglevel=6"
  )
}
exec sudo hyperkit2 "${args[@]}"
