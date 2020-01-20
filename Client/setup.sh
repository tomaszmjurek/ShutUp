#!/bin/bash

# Compile program
gcc -W client.c -o shutupcli
chmod +x shutupcli

# Move to stable dir
mkdir -p /etc/shutup
mv shutupcli /etc/shutup/

# Make script to run program on startup in background
echo '#!/bin/bash' > shutup.sh
echo './etc/shutup/shutupcli > /dev/null 2>&1 &' >> shutup.sh
chmod +x shutup.sh
mv -f shutup.sh /etc/init.d/
update-rc.d shutup.sh defaults

# Run program
cd /etc/shutup
./shutupcli


