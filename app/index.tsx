import { FIREBASE_AUTH } from '@/FirebaseConfig';
import React from 'react'
import { useState } from 'react';
import { View, StyleSheet, ActivityIndicator, Button } from 'react-native';
import { TextInput } from 'react-native-gesture-handler';
import {createUserWithEmailAndPassword, signInWithEmailAndPassword  } from "firebase/auth";

export default function Index() {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [loading, setLoading] = useState(false);
    const auth = FIREBASE_AUTH;

    const signUp = async () => {
        setLoading(true)
        try{
            const response = await createUserWithEmailAndPassword(auth, email, password);
            console.log(response);
        } catch (error: any) {
            console.log(error);
            alert("Sign up failed:" + error.message)
        } finally {
            setLoading(false);
        }
    };

    const signIn = async () => {
        setLoading(true)
        try{
            const response = await signInWithEmailAndPassword(auth, email, password);
            console.log(response);
        } catch (error: any){
            console.log(error);
            alert("Sign in failed:" + error.message)
        } finally {
            setLoading(false);
        }
    };


  return (
    <View style={styles.container}>
        <TextInput style={styles.input} value={email} placeholder='Email' autoCapitalize='none' onChangeText={(text) => setEmail(text)}/>
        <TextInput secureTextEntry={true} style={styles.input} value={password} placeholder='Password' autoCapitalize='none' onChangeText={(pass) => setPassword(pass)}/>
        {loading ? (<ActivityIndicator size="large" color="#000fff"/>) :
        (<>
        <Button title="Login" onPress={signIn}/>
        <Button title="Sign up" onPress={signUp}/>
        </>
        )
        }
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
        backgroundColor: '#fff',
    }
});