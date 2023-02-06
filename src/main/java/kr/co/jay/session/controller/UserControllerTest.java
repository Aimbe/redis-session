package kr.co.jay.session.controller;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @DisplayName("회원 가입 성공")
    @Test
    void signUpSuccess() throws Exception {
        // given
        SignUpRequest request = signUpRequest();
        UserResponse response = userResponse();

        doReturn(response).when(userService)
                .signUp(any(SignUpRequest.class));
    }

    private SignUpRequest signUpRequest() {
        return SignUpRequest.builder()
                .email("test@test.test")
                .pw("test")
                .build();
    }

    private UserResponse userResponse() {
        return UserResponse.builder()
                .email("test@test.test")
                .pw("test")
                .role(UserRole.ROLE_USER)
                .build();
    }

}