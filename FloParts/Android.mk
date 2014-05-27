LOCAL_PATH:= $(call my-dir)
ifneq ($(filter flo deb,$(TARGET_DEVICE)),)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_PACKAGE_NAME := FloParts
LOCAL_CERTIFICATE := platform

include $(BUILD_PACKAGE)
endif
