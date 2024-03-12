import React from 'react';
import {AppRegistry, StyleSheet, Text, View, Button} from 'react-native';
import { WebView } from 'react-native-webview';
import { SafeAreaProvider, SafeAreaView } from 'react-native-safe-area-context';
import AsyncStorage from '@react-native-async-storage/async-storage';

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
const asyncStorage = async () => {
  try {
    await AsyncStorage.setItem("my-key", "my-value");
    const value = await AsyncStorage.getItem("my-key");
    console.log(value);
  } catch (error) {
    console.log(error);
  }
};
const HelloWorld = () => {
  return (
    <View style={styles.container}>
      <Button title="asyncStorage" onPress={asyncStorage} />
    </View>
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
});

AppRegistry.registerComponent(
  'MyReactNativeApp',
  () => HelloWorld,
);