# LeoRover-SLAM-ROS2
Packages and instructions needed to launch the Leo System and attached rplidar in ROS2 Humble. Please install the Zed SDK, Zed-ros2-wrapper, Nav2, SLAM-Toolbox, and robot-localization packages for ROS2 Humble. Tested on Ubuntu 22.04 ROS2 Humble

## First Rover Use

If this is your first time activating or using the Leo Rover, please read INSTRUCTIONS.md located in this repo. 

## SSH Addresses and Passwords:

Addresses and passwords are located on the private rover document, however connection to the rover's onboard wifi, and ssh connections to the Raspberry Pi and Jetson are necessary to run the nodes on the rover.

## Android Project

For the Android project, all sections through "Startup Script" are necessary. The remaining sections are not needed for the project.


## Launching the Leo Rover

1. Open an ssh terminal on the raspberry pi and launch the Leo Rover's main system
```
$ source /opt/ros/humble/setup.bash
$ ros2 run leo_bringup leo_system
```
2. Open a second ssh terminal and launch the RPLidar node.
```.
$ source /opt/ros/humble/setup.bash
$ ros2 launch rplidar_ros rplidar_a2m12_launch.py
```
3. In order to get the Laserscan topic to appear correctly, and for the Nav2 SLAM to function with the ZED camera, we need to use a series of tf transfoms. All are necessary for the tf tree to be complete. If not working please use rqt.
```
$ source /opt/ros/humble/setup.bash
$ ros2 run tf2_ros static_transform_publisher 0.1 0 0.02 3.14159 0 0 base_link laser
$ ros2 run tf2_ros static_transform_publisher 0.1 0 0.1 3.14159 0 0 base_link zed_camera_link
$ ros2 run tf2_ros static_transform_publisher 0.1 0 0.1 3.14159 0 0 base_link zed_camera_center

```

## Setting up a control computer and using RVIZ
Requirements: Ubuntu 22.04 ROS2 Humble
1. Pull this repository to your local computer, build, and install any required dependencies.
```
$ git pull https://github.com/Arthios09/LeoRover-SLAM-ROS2.git
$ rosdep update
$ rosdep install --from-paths src -y --ignore-src
$ colcon build --symlink-install
```
2. Open a terminal within this folder so that we can visualize the system.
```
$ source install/setup.bash
$ ros2 launch leo_viz rviz.launch.xml
```
Note: All required packages are already on-board the Leo Rover.

## SLAM Implementation - Nav2
1. For installation of Nav2 and the required dependencies, please follow the guide at https://navigation.ros.org/getting_started/
2. To start the Nav2 + slamtec SLAM implementation, open 3 ssh terminals on the Nvidia Jetson and run:
```
$ source /opt/ros/humble/setup.bash
$ ros2 launch nav2_bringup navigation_launch.py params_file:=nav2_main.yaml
```
and
```
$ source /opt/ros/humble/setup.bash
$ ros2 launch slam_toolbox online_async_launch.py params_file:=mapper_params_main.yaml
```
and 
```
$ source /opt/ros/humble/setup.bash
$ ros2 launch robot_localization ekf.launch.py params_file:=ekf.yaml
```
The Zed Nodes may be launched with the following:
```
$ ros2 launch zed_wrapper zed_camera.launch.py camera_model:=zed2i  #zed node launch script

$ ros2 launch zed_aruco_localization zed_aruco_loc.launch.py camera_model:=zed2i params_file:=/Desktop/LeoRover/src/zed-ros2-examples/examples/zed_aruco_localization/config/aruco_loc.yaml
```
The custom drive/waypoint node may be ran with the following. The node sets a waypoint based on the current position, driving linearly x meters and rotating y degrees:
```
$ ros2 run drive_pkg drive_publisher --ros-args -p x:=(meters) -p y:=(degrees)
```

## API Server and Rosbridge Suite Websockets
1. Please clone the repository and follow directions found at https://gitlab.com/roar-gokart/api-server
```
$ cd .../api-server/
$ python main.py
```
2. To launch the websocket server:
```
$ ros2 launch rosbridge_server rosbridge_websocket_launch.xml
```

## Startup Script
1. To start all ROS2 nodes on the NVIDIA Jetson (Sections before this and after "Launching the Leo Rover"):
```
$ python3 start_rover.py
```


## Running Foxglove
Foxglove allows users with non Linux computers to receive an RVIZ-like visualization. Please use in this case:

1. Due to the way the websocket is set up, we need to export the port to point correctly on the rover computer. Open a terminal:
```
$ source install/setup.bash
$ ros2 launch foxglove_bridge foxglove_bridge_launch.xml port:=8765
```
2. Now, launch Foxglove Studio
```
$ foxglove-studio
```
Set the port to default (8765), and use the provided template file to view the rover. Files may change.
