# Build

Requirements:
- Docker CE
- LinuxKit from Git
- A docker image build from below

    FROM archlinux/base
    RUN echo 'Server = http://archlinux.cs.nctu.edu.tw/$repo/os/$arch' > $newroot/etc/pacman.d/mirrorlist \
        && pacman -Sy --noconfirm awk cpio diffutils dosfstools file grep parted tar vim which
    ADD rootfs /
    WORKDIR /work
    CMD [ "bash", "-l" ]

Steps:
1. Download busybox source from https://busybox.net/downloads/busybox-1.30.1.tar.bz2.
2. Download musl source from http://www.musl-libc.org/releases/musl-1.1.22.tar.gz.
3. Execute kernel-stuff/extract.
4. Execute bin/1-build-busybox.
5. Execute bin/2-pack-initrd.
6. Execute bin/3-create-system-disk.

# Run

Requirements:
- HyperKit from Git as hyperkit2

Steps:
1. Execute boot.
