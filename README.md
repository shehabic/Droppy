### Not currently under maintenance
I'm afraid I don't have enough time at the moment to maintain the lib, if anyone would like to participate in modernizing it you're more than welcome :)

---


[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Droppy-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1648)

Droppy
======

A simple yet-customizable Android drop-down menu. It supports Text with/without Icons, Separators, and even fully customized views.

Version
=======
v.0.6.0

Usage (Maven)
=============
```XML
<dependency>
    <groupId>com.shehabic.droppy</groupId>
    <artifactId>Droppy</artifactId>
    <version>0.6.0</version>
</dependency>
```

Usage (Gradle)
==============
```groovy
compile 'com.shehabic.droppy:Droppy:0.6.0@aar'
```

Generate Programmatically
=========================

```JAVA
// Assume we have a button in our Layout as follows
Buttton anchor = (Button) findViewById(R.id.button1);

DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(MyActivity.this, anchor);

// Add normal items (text only)
droppyBuilder.addMenuItem(new DroppyMenuItem("test1"))
    .addMenuItem(new DroppyMenuItem("test2"))
    .addSeparator();

// Add Item with icon
droppyBuilder.addMenuItem(new DroppyMenuItem("test3", R.drawable.ic_launcher));

// Add custom views
DroppyMenuCustomView sBarItem = new DroppyMenuCustomView(R.layout.slider);
droppyBuilder.addMenuItem(sBarItem);

// Set Callback handler
droppyBuilder.setOnClick(new DroppyClickCallbackInterface() {
    @Override
    public void call(View v, int id) {
        Log.d("Clicked on ", String.valueOf(id));
    }
});
        
DroppyMenuPopup droppyMenu = droppyBuilder.build();

// Then once you click on the button it'll show
// Alternatively you can call droppyMenu.show();
```

Generate From Menu Resource (XML)
=================================
given: ```src/main/res/menu/droppy.xml```

```XML
<menu xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      tools:context="com.shehabic.droppy_samples.MainActivity">
    <group android:enabled="true">
        <item
            android:id="@+id/dropp1"
            android:title="Xml Item 1"
            />

        <item
            android:id="@+id/droppy2"
            android:title="Xml Item 2"
            />
    </group>
    <group android:enabled="true">
        <item
            android:id="@+id/dropp3"
            android:icon="@drawable/ic_launcher"
            android:title="Xml Item 3"
            />

        <item
            android:id="@+id/droppy4"
            android:icon="@drawable/ic_launcher"
            android:title="Xml Item 4"
            />
    </group>
</menu>

```

We generate the menu as follows:

```JAVA
DroppyMenuPopup droppyMenu;
DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, btn);
DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.droppy)
    .triggerOnAnchorClick(false)
    .setOnClick(new DroppyClickCallbackInterface() {
        @Override
        public void call(View v, int id) {
            Log.d("Id:", String.valueOf(id));
        }
    })
    .setOnDismissCallback(DroppyMenuPopup.OnDismissCallback() {
        @Override
        public void call()
        {
            Toast.makeText(this, "Menu dismissed", Toast.LENGTH_SHORT).show();
        }
     })
    .setPopupAnimation(new DroppyFadeInAnimation())
    .setXOffset(5)
    .setYOffset(5)
    .build();
droppyMenu.show();
```

Customizing Syles
=================
```XML
    <style name="YourAppTheme" parent="Whatever.Parent.Theme.You.Are.Extending">
        <item name="droppyPopupStyle">@style/Your.Custom.DroppyPopup</item>
        <item name="droppyMenuStyle">@style/Your.Custom.DroppyMenu</item>
        <item name="droppyMenuSeparator">@style/Your.Custom.DroppyMenuSeparator</item>
        <item name="droppyMenuItemStyle">@style/Your.Custom.DroppyMenuItem</item>
        <item name="droppyMenuItemTitleStyle">@style/Your.Custom.DroppyMenuItemTitle</item>
        <item name="droppyMenuItemIconStyle">@style/Your.Custom.DroppyMenuItemIcon</item>
    </style>
    
    <!-- Your custom styles go here -->
    <style name="Your.Custom.DroppyPopup" parent="Droppy.DroppyPopup">
        <!-- Your Custom style attributes go here -->
    </style>
    <style name="Your.Custom.DroppyMenu" parent="Droppy.DroppyMenu">
        <!-- Your Custom style attributes go here -->
    </style>
    <style name="Your.Custom.DroppyMenuSeparator" parent="Droppy.DroppyMenuSeparator">
            <!-- Your Custom style attributes go here -->
        </style>
    <style name="Your.Custom.DroppyMenuItem" parent="Droppy.DroppyMenuItem">
        <!-- Your Custom style attributes go here -->
    </style>
    <style name="Your.Custom.DroppyMenuItemTitle" parent="Droppy.DroppyMenuItemTitle">
        <!-- Your Custom style attributes go here -->
    </style>
    <style name="Your.Custom.DroppyMenuItemIcon" parent="Droppy.DroppyMenuItemIcon">
        <!-- Your Custom style attributes go here -->
    </style>
```

How it looks like
=================
![](https://raw.githubusercontent.com/shehabic/Droppy/master/droppy-preview.gif)

![](https://raw.githubusercontent.com/shehabic/Droppy/screenshots/Droppy_Screenshot.png)

Why not the native PopupMenu?
=============================
Well if you have struggled long enough trying to show an icon beside the text in the normal popup menu or wanted to created a simple popup with custom view yest still contextual to an exisitng view, you would've know that it was almost impossible to do with the normal popup menu unless you use reflection and hack a the internal properties for PopupMenu and yet just add extra icon but not a fully customized view.

Reporting Bug / Opening Issues
==============================
  1-Please first make sure the issue you're reporting doesn't already exists to avoid duplicates.
  2-Please make sure you specify enough info if possible, including Target and Minimum SDK used, and portion of the code in addition to the stack trace/error message in case of errors.

Forks / Pull Request
====================
  As per the license you're free to fork the lib, and modify as you
  Pull-request for enhancements and bug-fixes ar always welcomed

Developed By
============
* Mohamed Shehab - <shehabic@gmail.com>


License
=======
    Copyright 2015-2016 Mohamed Shehab

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


