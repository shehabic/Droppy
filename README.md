# Droppy

A simple yet-customizable Android drop-down menu, support Icons, Text, Separators, and even fully Customized Views.

#Usage

```JAVA
// Assume we have a button in our Layout as follows
Buttton anchor = (Button) findViewById(R.id.button1);
        
DroppyMenu.Builder droppyBuilder = new DroppyMenu.Builder(MyActivity.this, anchor);
        
// Add normal items (text only)
droppyBuilder.addMenuItem(new DroppyMenuItem("test1"));
droppyBuilder.addMenuItem(new DroppyMenuItem("test2"));
droppyBuilder.addSeparator();
        
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
