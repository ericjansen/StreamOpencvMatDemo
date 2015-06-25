TEMPLATE = app
CONFIG += console
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += main.cpp

INCLUDEPATH += D:/sdk/opencv/opencv/build/include \
INCLUDEPATH += D:/sdk/opencv/opencv/build/include/opencv


LIBS += -LD:/sdk/opencv/opencv/build/x64/vc12/lib \
    -lopencv_calib3d249d \
    -lopencv_contrib249d \
    -lopencv_core249d \
    -lopencv_features2d249d \
    -lopencv_flann249d \
    -lopencv_gpu249d \
    -lopencv_highgui249d \
    -lopencv_imgproc249d \
    -lopencv_legacy249d \
    -lopencv_ml249d \
    -lopencv_nonfree249d \
    -lopencv_objdetect249d \
    -lopencv_ocl249d \
    -lopencv_photo249d \
    -lopencv_stitching249d \
    -lopencv_superres249d \
    -lopencv_ts249d \
    -lopencv_video249d \
    -lopencv_videostab249d

INCLUDEPATH += $$PWD
INCLUDEPATH += d:/sdk/boost_1_56_0

LIBS += -Ld:/sdk/boost_1_56_0/lib64-msvc-12.0  \
	-lboost_system-vc120-mt-1_56 \
	-lboost_thread-vc120-mt-1_56 \
	-llibboost_system-vc120-mt-1_56 \
	-llibboost_thread-vc120-mt-1_56

include(deployment.pri)
qtcAddDeployment()

