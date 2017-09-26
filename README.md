# SlideDownMessage
[![Releases](https://jitpack.io/v/FabianLockhorst/SlideDownMessage.svg)](https://jitpack.io/#FabianLockhorst/SlideDownMessage)

A Simple slide down message for android to notify the user that for example the app is currently connecting.

# Examples
* <a href="https://thumbs.gfycat.com/WarpedDimGreyhounddog-size_restricted.gif" target="_blank">Short</a>
* <a href="https://thumbs.gfycat.com/HighlevelResponsibleInchworm-size_restricted.gif" target="_blank">Long</a>
* <a href="https://thumbs.gfycat.com/AromaticMildBillygoat-size_restricted.gif" target="_blank">Show/Hide</a>

[Example code](https://github.com/FabianLockhorst/SlideDownMessage/blob/master/app/src/main/java/com/fabianlockhorst/slidedownmessage/MainActivity.java)

## Table of Contents
* [Installation](#installation)
* [Usage](#usage)
* [License](#License)



## <a name=installation>Installation</a>

In your project level build.gradle :

```java
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

In your app level build.gradle :

```java
dependencies {
  compile 'com.github.fabianlockhorst:slidedownmessage:1.0.0'
}
```
## <a name=usage>Usage</a>
Basic usage

```java
SlideDownMessage.make(View, "message", context).show();
```
The view parameter can be for example the activity root or fragment root.

The SlideDownMessage has three different timings
* LENGTH_INDEFINITELY
* LENGTH_SHORT (2000ms)
* LENGTH_LONG (3500ms) *default*

You can set the lengt using the make function
```java
SlideDownMessage.make(View, "message", context, SlideDownMessage.LENGTH_SHORT).show();
```

If LENGTH_INDEFINITELY is set you must keep a refrence of the SlideDownMessage to hide it again
```java
 SlideDownMessage message;
 message = SlideDownMessage.make(View, "message", context, SlideDownMessage.LENGTH_INDEFINITELY);
 
 //show
 message.show();
 
 //hide
 message.hide();
```

If you want to customize the layout in any way you can retrieve the view using the getView() method
<a href="https://thumbs.gfycat.com/JoyfulSlushyAustraliancattledog-size_restricted.gif" target="_blank">Like here</a>
```java
//change background color to colorPrimary
SlideDownMessage message;
message = SlideDownMessage.make(View, "message", context, SlideDownMessage.LENGTH_SHORT);
message.getView().setBackgroundResource(R.color.colorPrimary);
message.show();
```


# Custom offset or toolbar
If you add the message to the root of an activity that uses the V7 toolbar you can set hasToolbar to true.
it will use the theme actionBarSize as a offset. 
```java
SlideDownMessage.make(View, "message", context, SlideDownMessage.LENGTH_SHORT, true).show();
```
if the message needs to come down further you can set a custom offset.
```java
SlideDownMessage.make(View, "message", context, SlideDownMessage.LENGTH_SHORT, 168).show();
```


## <a name=License>License</a>
```
Copyright 2017 Fabian Lockhorst

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
