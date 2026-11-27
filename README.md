# DTA Emulator
This project is an emulator for a simple DTA architecture processor made from scratch in java, with an inbuilt assembler and flatLaf dark themed GUI.

### Also see the second repo, [DTA-CPU](https://www.github.com/koino2/DTA-CPU)
Mainly for ISA, test programs, compilation and other stuff.

> ### Warning
> This software has been built for only Windows machines, and the GUI is fixed-scale and optimized for 1080p monitors only.  
> 
> Also, the emulator simulates an entire DTA Processor with all its hardware features, so performance will be bad on lower end computers and laptops. From my experience, it cannot handle more than a 1000:1 clock:update ratio on my i7-12700H. (clock:update is the ratio of clock speed to GUI update frequency. clock speeds can go up to hundreds of megahertz if the update frequency stays at around 1/10th to 1/100th of that)  
> 
> It may work poorly on other computers, if at all.

## Usage
Download the release zip from the releases tab on github, unzip it and run ``DTA-Emulator.exe``. Make sure the ``assets``, ``runtime``, and ``app`` folders is in the same working directory as the program.

Alternatively, clone the Github repo and build it yourself with main class ``Main`` and also have the library FlatLaf installed (from maven or otherwise).

### **Follow the tutorial in HowToRunPrograms.md (in the project directory or from https://github.com/koino2/DTA-Emulator/blob/master/HowToRunPrograms.md) to start using the processor!**

## ISA / CPU Architecture
The project emulates a DTA (Data Transfer Architecture) processor, which is a minimal, 3 instruction ISA.  

The full documentation can be read at https://github.com/koino2/DTA-CPU/blob/master/spec-1/DTA.md

## GUI
This project features a full GUI interface that shows all the information about the simulated processor, with a flatLaf dark theme.

The GUI features interfaces for controlling and viewing the processor. It has controls for every register, pin, device and component. It also allows you to develop applications with the assembler and start running them.

### Attributions
#### FlatLaf
The FlatLaf look and feel was used for the GUI theme.  
License can be found at ``licenses/flatlaf_license.txt`` (sourced from https://github.com/JFormDesigner/FlatLaf/blob/main/LICENSE)
