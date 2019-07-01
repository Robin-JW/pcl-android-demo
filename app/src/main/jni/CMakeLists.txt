cmake_minimum_required(VERSION 3.4.1)

###################
##               ##
###################

#
set(PCL_BUILD $ENV{PCL_ANDROID})

set(PCL_INCLUDE "${PCL_BUILD}/pcl-android")
set(BOOST_INCLUDE "${PCL_BUILD}/boost-android")
set(FLANN_INCLUDE "${PCL_BUILD}/flann-android")
set(EIGEN_INCLUDE "${PCL_BUILD}/eigen")
set(QHULL_INCLUDE "${PCL_BUILD}/qhull-android")

set(PCL_STATIC_LIB_DIR "${PCL_INCLUDE}/lib")
set(BOOST_STATIC_LIB_DIR "${BOOST_INCLUDE}/lib")
set(FLANN_STATIC_LIB_DIR "${FLANN_INCLUDE}/lib")
set(QHULL_STATIC_LIB_DIR "${QHULL_INCLUDE}/lib")

include_directories(${PCL_INCLUDE}/include)
include_directories(${BOOST_INCLUDE}/include)
include_directories(${FLANN_INCLUDE}/include)
include_directories(${QHULL_INCLUDE}/include)
include_directories(${EIGEN_INCLUDE})


function(importLib dirName libName)
    add_library(${libName} STATIC IMPORTED)
    set_target_properties(${libName}
            PROPERTIES
            IMPORTED_LOCATION
            "${dirName}/${libName}.a")
endfunction(importLib)

set(libFlannNames
        libflann_cpp_s
        libflann_s
        )

set(libBoostNames
        libboost_thread
        libboost_signals
        libboost_program_options
        libboost_filesystem
        libboost_system
        libboost_regex
        libboost_iostreams
        libboost_date_time
        )

set(libQhullNames
        libqhullstatic
        )

set(libPclNames
        libpcl_stereo
        libpcl_2d
        libpcl_segmentation
        libpcl_recognition
        libpcl_ml
        libpcl_io
        libpcl_io_ply
        libpcl_filters
        libpcl_tracking
        libpcl_search
        libpcl_registration
        libpcl_keypoints
        libpcl_features
        libpcl_surface
        libpcl_sample_consensus
        libpcl_octree
        libpcl_kdtree
        libpcl_common
        )

foreach(lib ${libPclNames})
    importLib(${PCL_STATIC_LIB_DIR} ${lib})
endforeach(lib)

foreach(lib ${libFlannNames})
    importLib(${FLANN_STATIC_LIB_DIR} ${lib})
endforeach(lib)

foreach(lib ${libBoostNames})
    importLib(${BOOST_STATIC_LIB_DIR} ${lib})
endforeach(lib)

foreach(lib ${libQhullNames})
    importLib(${QHULL_STATIC_LIB_DIR} ${lib})
endforeach(lib)

add_library(native-jni SHARED
        native-jni.cpp
        )

target_link_libraries(
        native-jni

        ${libPclNames}
        ${libFlannNames}
        ${libBoostNames}
        ${libQhullNames}

        android
        log)