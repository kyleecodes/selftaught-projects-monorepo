# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.15

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2019.3.4\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2019.3.4\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "C:\Users\kmfie\Desktop\homework-bleh\operating systems\projects\Lab4_C"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "C:\Users\kmfie\Desktop\homework-bleh\operating systems\projects\Lab4_C\cmake-build-debug"

# Include any dependencies generated for this target.
include CMakeFiles/Lab4_C.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Lab4_C.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Lab4_C.dir/flags.make

CMakeFiles/Lab4_C.dir/main.cpp.obj: CMakeFiles/Lab4_C.dir/flags.make
CMakeFiles/Lab4_C.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="C:\Users\kmfie\Desktop\homework-bleh\operating systems\projects\Lab4_C\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Lab4_C.dir/main.cpp.obj"
	C:\MinGW\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\Lab4_C.dir\main.cpp.obj -c "C:\Users\kmfie\Desktop\homework-bleh\operating systems\projects\Lab4_C\main.cpp"

CMakeFiles/Lab4_C.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Lab4_C.dir/main.cpp.i"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "C:\Users\kmfie\Desktop\homework-bleh\operating systems\projects\Lab4_C\main.cpp" > CMakeFiles\Lab4_C.dir\main.cpp.i

CMakeFiles/Lab4_C.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Lab4_C.dir/main.cpp.s"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "C:\Users\kmfie\Desktop\homework-bleh\operating systems\projects\Lab4_C\main.cpp" -o CMakeFiles\Lab4_C.dir\main.cpp.s

# Object files for target Lab4_C
Lab4_C_OBJECTS = \
"CMakeFiles/Lab4_C.dir/main.cpp.obj"

# External object files for target Lab4_C
Lab4_C_EXTERNAL_OBJECTS =

Lab4_C.exe: CMakeFiles/Lab4_C.dir/main.cpp.obj
Lab4_C.exe: CMakeFiles/Lab4_C.dir/build.make
Lab4_C.exe: CMakeFiles/Lab4_C.dir/linklibs.rsp
Lab4_C.exe: CMakeFiles/Lab4_C.dir/objects1.rsp
Lab4_C.exe: CMakeFiles/Lab4_C.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="C:\Users\kmfie\Desktop\homework-bleh\operating systems\projects\Lab4_C\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable Lab4_C.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\Lab4_C.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Lab4_C.dir/build: Lab4_C.exe

.PHONY : CMakeFiles/Lab4_C.dir/build

CMakeFiles/Lab4_C.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\Lab4_C.dir\cmake_clean.cmake
.PHONY : CMakeFiles/Lab4_C.dir/clean

CMakeFiles/Lab4_C.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" "C:\Users\kmfie\Desktop\homework-bleh\operating systems\projects\Lab4_C" "C:\Users\kmfie\Desktop\homework-bleh\operating systems\projects\Lab4_C" "C:\Users\kmfie\Desktop\homework-bleh\operating systems\projects\Lab4_C\cmake-build-debug" "C:\Users\kmfie\Desktop\homework-bleh\operating systems\projects\Lab4_C\cmake-build-debug" "C:\Users\kmfie\Desktop\homework-bleh\operating systems\projects\Lab4_C\cmake-build-debug\CMakeFiles\Lab4_C.dir\DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/Lab4_C.dir/depend

