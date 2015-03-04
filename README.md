Droppy
======

A simple yet-customizable Android drop-down menu. It supports Text with/without Icons, Separators, and even fully customized views.

Version
=======
v.0.1 beta

Usage (Maven)
=============
```XML
<dependency>
  <groupId>com.shehabic.droppy</groupId>
  <artifactId>Droppy</artifactId>
  <version>0.1</version>
</dependency>
```

Usage (Gradle)
==============
```groovy
compile 'com.shehabic.droppy:Droppy:0.1@aar'
```

Generate Programmatically
=========================

```JAVA
// Assume we have a button in our Layout as follows
Buttton anchor = (Button) findViewById(R.id.button1);

DroppyMenu.Builder droppyBuilder = new DroppyMenu.Builder(MyActivity.this, anchor);

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
        
DroppyMenu droppyMenu = droppyBuilder.build();

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
DroppyMenu droppyMenu;
DroppyMenu.Builder droppyBuilder = new DroppyMenu.Builder(this, btn);
DroppyMenu droppyMenu = droppyBuilder.fromMenu(R.menu.droppy)
    .triggerOnAnchorClick(false)
    .setOnClick(new DroppyClickCallbackInterface() {
        @Override
        public void call(View v, int id) {
            Log.d("Id:", String.valueOf(id));
        }
    })
    .build();
droppyMenu.show();
```

How it looks like
=================
![](https://raw.githubusercontent.com/shehabic/Droppy/screenshots/Droppy_Screenshot.png)

Why not the native PopupMenu?
=============================
Well if you have struggled long enough trying to show an icon beside the text in the normal popup menu or wanted to created a simple popup with custom view yest still contextual to an exisitng view, you would've know that it was almost impossible to do with the normal popup menu unless you use reflection and hack a the internal properties for PopupMenu and yet just add extra icon but not a fully customized view.

Developed By
============

* Mohamed Shehab - <shehabic@gmail.com>


License
=======

    Copyright 2015 Mohamed Shehab

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


