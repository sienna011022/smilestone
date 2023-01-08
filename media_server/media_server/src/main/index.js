import express from 'express';
import http from 'http';
import { Server } from "socket.io";
import {createWorker} from "mediasoup";

const app = express();
const server = http.createServer(app);
const io = new Server(server, {
    cors: {
        origin: "*"
    }
});

const client = io.of('/mediasoup')