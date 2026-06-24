cmake_minimum_required(VERSION 3.19)
project(inventoryApp LANGUAGES CXX)

find_package(Qt6 6.5 REQUIRED COMPONENTS Core Widgets Sql)
find_package(Qt6 REQUIRED COMPONENTS Core)

qt_standard_project_setup()

qt_add_executable(inventoryApp
    WIN32 MACOSX_BUNDLE
    main.cpp
    mainwindow.cpp
    mainwindow.h
    mainwindow.ui
    ScannerIndex.h
    database.h database.cpp
    product.h
    cache.h
    utilities.h utilities.cpp


)

target_link_libraries(inventoryApp
    PRIVATE
        Qt::Core
        Qt::Widgets
)
target_link_libraries(inventoryApp PRIVATE Qt6::Core Qt6::Sql)

include(GNUInstallDirs)

install(TARGETS inventoryApp
    BUNDLE  DESTINATION .
    RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
)

qt_generate_deploy_app_script(
    TARGET inventoryApp
    OUTPUT_SCRIPT deploy_script
    NO_UNSUPPORTED_PLATFORM_ERROR
)
install(SCRIPT ${deploy_script})
