# How to run a program
This tutorial is for people who don't care about the ISA and just want to run  programs on it.

Here, I will walk you through the steps to start running a program of a 3D cube renderer.

- Download the program code from **insert link here**
- Open the app, choose **Import program** in the top bar and open that program file.
- Set the **ROM size to 3000** (in the memory section).
- Click on the **write program** button, below the code section.
- Near the top of the app, change the **update frequency to 10000**.
- In the clock section, set the **clock speed to 500000**.
- Click on **Edit Devices** (below the clock section)
- Increase the **device number to 1**.
- Change the device from Empty to a **CDA GPU**
- Click **Confirm**
- Click on the **Start Clock** button.

Now, you should start seeing a spinning 3D cube in the device section while the processor is emulating the program. Congrats!