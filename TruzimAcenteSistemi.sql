PGDMP  8    1                |            TruzimAcenteSistemi    16.2    16.2 '    2           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            3           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            4           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            5           1262    16441    TruzimAcenteSistemi    DATABASE     w   CREATE DATABASE "TruzimAcenteSistemi" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
 %   DROP DATABASE "TruzimAcenteSistemi";
                postgres    false            �            1259    16450    user    TABLE     �   CREATE TABLE public."user" (
    id integer NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16449    User_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."User_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �            1259    16458    hotel    TABLE       CREATE TABLE public.hotel (
    id integer NOT NULL,
    hotel_name text NOT NULL,
    address text NOT NULL,
    email text NOT NULL,
    phone text NOT NULL,
    star integer NOT NULL,
    pansiyon text NOT NULL,
    tesis text NOT NULL,
    sezon text NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16457    hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.hotel_id_seq;
       public          postgres    false    218            6           0    0    hotel_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.hotel_id_seq OWNED BY public.hotel.id;
          public          postgres    false    217            �            1259    16494    reservation    TABLE     C  CREATE TABLE public.reservation (
    id integer NOT NULL,
    city character varying(100),
    room_id integer NOT NULL,
    season_choice character varying(10) NOT NULL,
    room_type character varying(50) NOT NULL,
    name_surname character varying(100) NOT NULL,
    phone_num character varying(15) NOT NULL,
    mail character varying(100) NOT NULL,
    hotel_name character varying(200),
    entry_date character varying(100) NOT NULL,
    exit_date character varying(100) NOT NULL,
    people_number integer,
    child_number integer,
    price character varying(100)
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16493    reservation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.reservation_id_seq;
       public          postgres    false    224            7           0    0    reservation_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;
          public          postgres    false    223            �            1259    16467    room    TABLE     M  CREATE TABLE public.room (
    id integer NOT NULL,
    room_size text,
    hotel_id integer,
    television boolean,
    mini_bar boolean,
    game_console boolean,
    safe boolean,
    projection boolean,
    price text,
    bed_number integer,
    availability boolean,
    room_type text NOT NULL,
    stock integer NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16466    room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.room_id_seq;
       public          postgres    false    220            8           0    0    room_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.room_id_seq OWNED BY public.room.id;
          public          postgres    false    219            �            1259    16482    season    TABLE     u   CREATE TABLE public.season (
    id integer NOT NULL,
    room_id integer,
    season_hotel character varying(10)
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    16481    season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.season_id_seq;
       public          postgres    false    222            9           0    0    season_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.season_id_seq OWNED BY public.season.id;
          public          postgres    false    221            �           2604    16461    hotel id    DEFAULT     d   ALTER TABLE ONLY public.hotel ALTER COLUMN id SET DEFAULT nextval('public.hotel_id_seq'::regclass);
 7   ALTER TABLE public.hotel ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            �           2604    16497    reservation id    DEFAULT     p   ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);
 =   ALTER TABLE public.reservation ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223    224            �           2604    16470    room id    DEFAULT     b   ALTER TABLE ONLY public.room ALTER COLUMN id SET DEFAULT nextval('public.room_id_seq'::regclass);
 6   ALTER TABLE public.room ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    16485 	   season id    DEFAULT     f   ALTER TABLE ONLY public.season ALTER COLUMN id SET DEFAULT nextval('public.season_id_seq'::regclass);
 8   ALTER TABLE public.season ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222            )          0    16458    hotel 
   TABLE DATA           d   COPY public.hotel (id, hotel_name, address, email, phone, star, pansiyon, tesis, sezon) FROM stdin;
    public          postgres    false    218   �+       /          0    16494    reservation 
   TABLE DATA           �   COPY public.reservation (id, city, room_id, season_choice, room_type, name_surname, phone_num, mail, hotel_name, entry_date, exit_date, people_number, child_number, price) FROM stdin;
    public          postgres    false    224   z-       +          0    16467    room 
   TABLE DATA           �   COPY public.room (id, room_size, hotel_id, television, mini_bar, game_console, safe, projection, price, bed_number, availability, room_type, stock) FROM stdin;
    public          postgres    false    220   D.       -          0    16482    season 
   TABLE DATA           ;   COPY public.season (id, room_id, season_hotel) FROM stdin;
    public          postgres    false    222   �.       '          0    16450    user 
   TABLE DATA           >   COPY public."user" (id, username, password, role) FROM stdin;
    public          postgres    false    216   �.       :           0    0    User_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public."User_id_seq"', 7, true);
          public          postgres    false    215            ;           0    0    hotel_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.hotel_id_seq', 7, true);
          public          postgres    false    217            <           0    0    reservation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservation_id_seq', 19, true);
          public          postgres    false    223            =           0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 12, true);
          public          postgres    false    219            >           0    0    season_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.season_id_seq', 1, false);
          public          postgres    false    221            �           2606    16456    user User_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);
 <   ALTER TABLE ONLY public."user" DROP CONSTRAINT "User_pkey";
       public            postgres    false    216            �           2606    16465    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    218            �           2606    16499    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    224            �           2606    16474    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    220            �           2606    16487    season season_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    222            �           2606    16476    room fk_hotel_id    FK CONSTRAINT     p   ALTER TABLE ONLY public.room
    ADD CONSTRAINT fk_hotel_id FOREIGN KEY (hotel_id) REFERENCES public.hotel(id);
 :   ALTER TABLE ONLY public.room DROP CONSTRAINT fk_hotel_id;
       public          postgres    false    218    3470    220            �           2606    16488    season fk_room_id    FK CONSTRAINT     o   ALTER TABLE ONLY public.season
    ADD CONSTRAINT fk_room_id FOREIGN KEY (room_id) REFERENCES public.room(id);
 ;   ALTER TABLE ONLY public.season DROP CONSTRAINT fk_room_id;
       public          postgres    false    220    222    3472            )   �  x�Ւ�N�@���O1���o�B�	�C��7#Lde�m�[}_A����11zi�=�w���g�=�o�z,f˷z,%d������SJs�� K�8�GG��'�H��W�<�!����jءIY�=UA�jL����6�?c_�{ڱU��y�9�B�2��5�.�נaW��x�E�ͦ��P{v��+�5�Kz�	b8+uA�'I^���0���Q���~r��~�3��r�����3���@:����������8�VJBz�$Ip���4nZ[�'^{P�@oHإф�Y�m��7Im������@�)D�=���h�މ�p�~�^���R:�FqQ�9+c]83.�gj X?�wiO|Z��y9q�z���v!��!��VtN      /   �   x�e�=�0�g�9A���-#0����*�D���i��l�{�!�e�-����`aOi���uw{^`�ڬ`�u�b��i���s W��L`Th ��(�T��N���Mj����]n����L����&��d�ܫS~f'�l�/�G�9�	x\C K�j*k��y�OcOV^!��D�I���]Y,      +   {   x�e��
�@Cϙ��d2�Z�=�X��
]Y�.��ݶ�$0L�#���M�.�4��,����T�櫰��?�t��ҤCe�������a���@�r�u��($̡�ߵ�n-C)"+2�0w      -      x������ � �      '   O   x�3�LL����4426�0��9K�S�8M��9Ssr�+SS�L���Fh�f���ťi�)���E�G6��xx9B:F��� ���     