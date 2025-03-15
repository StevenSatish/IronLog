import { FIREBASE_AUTH } from '@/FirebaseConfig';
import React from 'react'
import { useState } from 'react';
import { View, StyleSheet } from 'react-native';
import { TextInput } from 'react-native-gesture-handler';

function Login() {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [loading, setLoading] = useState(false);
    const auth = FIREBASE_AUTH;

  return (
    <View style={styles.container}>
        <TextInput style={styles.input} value ={email} placeholder='Email' autoCapitalize='none' onChangeText={(text) => setEmail(text)}/>
        <TextInput secureTextEntry={true} style={styles.input} value ={password} placeholder='Password' autoCapitalize='none' onChangeText={(pass) => setPassword(pass)}/>
    </View>
  );
}

const styles = StyleSheet.create({
    container: {
        marginHorizontal: 20,
        flex: 1,
        justifyContent: 'center'
    },
    input: {
        marginVertical: 4,
        height: 50,
        borderWidth: 1,
        borderRadius: 4,
        padding: 10,
        backgroundColor: 'fff',
    }
});

export default Login