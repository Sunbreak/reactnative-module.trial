import React from 'react';
import {AppRegistry, StyleSheet, Text, View} from 'react-native';
import { WebView } from 'react-native-webview';
import { SafeAreaProvider, SafeAreaView } from 'react-native-safe-area-context';

// react-native-webview
//const HelloWorld = () => {
//  return <WebView source={{ uri: 'https://reactnative.dev/' }} style={{ flex: 1 }} />;
//};

// react-native-safe-area-context
const HelloWorld = () => {
  return (
    <SafeAreaProvider>
      <SafeAreaView style={{ flex: 1, backgroundColor: 'red' }}>
        <View style={{ flex: 1, backgroundColor: 'blue' }} />
      </SafeAreaView>
    </SafeAreaProvider>
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