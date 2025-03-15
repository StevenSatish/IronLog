import { Stack } from "expo-router";
import { StatusBar } from 'expo-status-bar';
import React from 'react';

export default function RootLayout() {
  return (
    <>
      <Stack initialRouteName="Login">
        <Stack.Screen name="Login" options={{ headerShown: false }} />
      </Stack>
      <StatusBar style="light" />
    </>
  );
}
