#include <dirent.h>
#include <iostream>

int main() {
    printf("DT_DIR: '%u'\n", DT_DIR);
    printf("DT_LNK: '%u'\n", DT_LNK);
    printf("DT_REG: '%u'\n", DT_REG);

    return 0;
}
