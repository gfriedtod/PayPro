import {AdminDto} from './AdminDto';

export interface LoginRequest{
  email: string;
  password: string;
}

export interface LoginResponse{
  token: string,
  user: AdminDto
}