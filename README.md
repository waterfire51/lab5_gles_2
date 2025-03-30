Lab5_GLES – OpenGL ES Scene Application

Student: Kazım Yıldırım
Class: IM-14
Instructor: Victor Porev
Course: Computer Graphics Programming

🎯 Project Overview
This project implements a 3D scene application using OpenGL ES 2.0 in Android Studio. It includes:
3 different rendering modes (Torus, Earth, Seaview)
Real-time camera control (rotation and zoom)
Textures, lighting, skybox, and scene switching

⚙️ Technologies Used
OpenGL ES 2.0
Android Java (GLSurfaceView, Shader, Matrix, Touch Controls)
Custom geometries (Torus, Sphere, Plane)
Textures (Earth map, Chessboard, Skybox image)


🔁 Mode 1: Torus Mode
Displays a spinning textured torus
A chessboard lies under the torus as ground
MVP Matrix used for rotation
📷 Screenshot:
<img src="https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExc3NteTZ0eXU5OXM2Zmx4Y2thZHkyNXVndmtyc3o1cGZsMG1taHY1ayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/5T82aJDuA4Frsprku6/giphy.gif" width="500"/>



🌍 Mode 2: Earth Mode
Displays a textured sphere with an Earth map
Camera control enables full viewing
Texture mapped on sphere vertices
📷 Screenshot:
<img src="https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExM2ZpNW5uMm1lZHR3YmhvZjIwY29lbDd2YnB3Mjc2dWh6M2YzMTNkaCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/MPyUgd3F0Kh3FLXycZ/giphy.gif" width="500"/>


🌌 Mode 3: Seaview Mode
Skybox cube drawn using custom coordinates
Inside the cube: a torus and chessboard
Texture used for immersive space effect
📷 Screenshot:
<img src="https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExNG1lZmxqdHl6aThleTZ5ZmwwYzYzMTB1bXVudDNmcmJ0anlzN2Q4aSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/nfmHoQg3f8DIcx1PW1/giphy.gif" width="500"/>


🧭 Camera Control
Touch events control rotation (X and Y)
Two-finger pinch controls zoom
Custom renderer interprets gestures

🧱 Code Architecture
MainActivity.java → mode selection
SceneActivity.java → renders selected mode
MyGLSurfaceView → handles rendering + interaction
MyGLRenderer → manages view/projection matrix and rendering logic
Separate classes for each scene mode (TorusMode, EarthMode, SeaviewMode)
TextureHelper.java to load bitmap images

✅ Results and Experience
Learned practical OpenGL matrix math
Built shader programs
Improved scene management and code modularity
Resolved rendering and alignment bugs


