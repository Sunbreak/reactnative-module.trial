import React from 'react';
import {AppRegistry, StyleSheet, Text, View, Button} from 'react-native';
import { WebView } from 'react-native-webview';
import { SafeAreaProvider, SafeAreaView } from 'react-native-safe-area-context';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { fetch } from "@react-native-community/netinfo";
import { GestureHandlerRootView, TapGestureHandler, State } from 'react-native-gesture-handler';

// react-native-webview
//const HelloWorld = () => {
//  return <WebView source={{ uri: 'https://reactnative.dev/' }} style={{ flex: 1 }} />;
//};

// react-native-safe-area-context
//const HelloWorld = () => {
//  return (
//    <SafeAreaProvider>
//      <SafeAreaView style={{ flex: 1, backgroundColor: 'red' }}>
//        <View style={{ flex: 1, backgroundColor: 'blue' }} />
//      </SafeAreaView>
//    </SafeAreaProvider>
//  );
//};

// @react-native-async-storage/async-storage
//const asyncStorage = async () => {
//  try {
//    await AsyncStorage.setItem("my-key", "my-value");
//    const value = await AsyncStorage.getItem("my-key");
//    console.log(value);
//  } catch (error) {
//    console.log(error);
//  }
//};
//const HelloWorld = () => {
//  return (
//    <View style={styles.container}>
//      <Button title="asyncStorage" onPress={asyncStorage} />
//    </View>
//  );
//};

// react-native-screen
//const Stack = createNativeStackNavigator();
//function HomeScreen({ navigation }) {
//  return (
//    <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
//      <Text>Home Screen</Text>
//      <Button
//        title="Go to About"
//        onPress={() => navigation.navigate("About")}
//      />
//    </View>
//  );
//}
//function AboutScreen() {
//  return (
//    <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
//      <Text>About Screen</Text>
//    </View>
//  );
//}
//const HelloWorld = () => {
//  return (
//    <NavigationContainer>
//      <Stack.Navigator>
//        <Stack.Screen
//          name="Home"
//          component={HomeScreen}
//        />
//        <Stack.Screen
//          name="About"
//          component={AboutScreen}
//        />
//      </Stack.Navigator>
//    </NavigationContainer>
//  );
//};

// @react-native-community/netinfo
//const fetchNetInfo = async () => {
//  const { type, isConnected } = await fetch();
//  console.log("type", type, "isConnected", isConnected);
//};
//const HelloWorld = () => {
//  return (
//    <View style={styles.container}>
//      <Button title="fetchNetInfo" onPress={fetchNetInfo} />
//    </View>
//  );
//};

// react-native-gesture-handler
const HelloWorld = () => {
  const onSingleTapEvent = (event) => {
    if (event.nativeEvent.state === State.ACTIVE) {
      console.log('Hey single tap!');
    }
    console.log('aaa');
  };

  return (
    <GestureHandlerRootView style={styles.container}>
      <Text>Single Tap Gesture Handler</Text>
      <TapGestureHandler onHandlerStateChange={onSingleTapEvent}>
        <View style={styles.square} />
      </TapGestureHandler>
    </GestureHandlerRootView>
  );
};

var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  square: {
    width: 150,
    height: 150,
    backgroundColor: '#28b5b5',
    marginTop: 22,
  },
});

AppRegistry.registerComponent(
  'MyReactNativeApp',
  () => HelloWorld,
);