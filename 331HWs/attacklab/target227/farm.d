
farm.o:     file format elf64-x86-64


Disassembly of section .text:

0000000000000000 <start_farm>:
   0:	55                   	push   %rbp
   1:	48 89 e5             	mov    %rsp,%rbp
   4:	b8 01 00 00 00       	mov    $0x1,%eax
   9:	5d                   	pop    %rbp
   a:	c3                   	retq   

000000000000000b <addval_392>:
   b:	55                   	push   %rbp
   c:	48 89 e5             	mov    %rsp,%rbp
   f:	89 7d fc             	mov    %edi,-0x4(%rbp)
  12:	8b 45 fc             	mov    -0x4(%rbp),%eax
  15:	2d b8 76 21 3c       	sub    $0x3c2176b8,%eax
  1a:	5d                   	pop    %rbp
  1b:	c3                   	retq   

000000000000001c <getval_426>:
  1c:	55                   	push   %rbp
  1d:	48 89 e5             	mov    %rsp,%rbp
  20:	b8 48 89 c1 90       	mov    $0x90c18948,%eax
  25:	5d                   	pop    %rbp
  26:	c3                   	retq   

0000000000000027 <addval_465>:
  27:	55                   	push   %rbp
  28:	48 89 e5             	mov    %rsp,%rbp
  2b:	89 7d fc             	mov    %edi,-0x4(%rbp)
  2e:	8b 45 fc             	mov    -0x4(%rbp),%eax
  31:	2d b8 76 3c 3c       	sub    $0x3c3c76b8,%eax
  36:	5d                   	pop    %rbp
  37:	c3                   	retq   

0000000000000038 <getval_143>:
  38:	55                   	push   %rbp
  39:	48 89 e5             	mov    %rsp,%rbp
  3c:	b8 d7 58 92 90       	mov    $0x909258d7,%eax
  41:	5d                   	pop    %rbp
  42:	c3                   	retq   

0000000000000043 <setval_373>:
  43:	55                   	push   %rbp
  44:	48 89 e5             	mov    %rsp,%rbp
  47:	48 89 7d f8          	mov    %rdi,-0x8(%rbp)
  4b:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
  4f:	c7 00 58 90 92 c3    	movl   $0xc3929058,(%rax)
  55:	90                   	nop
  56:	5d                   	pop    %rbp
  57:	c3                   	retq   

0000000000000058 <getval_389>:
  58:	55                   	push   %rbp
  59:	48 89 e5             	mov    %rsp,%rbp
  5c:	b8 48 89 d7 90       	mov    $0x90d78948,%eax
  61:	5d                   	pop    %rbp
  62:	c3                   	retq   

0000000000000063 <setval_225>:
  63:	55                   	push   %rbp
  64:	48 89 e5             	mov    %rsp,%rbp
  67:	48 89 7d f8          	mov    %rdi,-0x8(%rbp)
  6b:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
  6f:	c7 00 48 89 c1 c3    	movl   $0xc3c18948,(%rax)
  75:	90                   	nop
  76:	5d                   	pop    %rbp
  77:	c3                   	retq   

0000000000000078 <getval_431>:
  78:	55                   	push   %rbp
  79:	48 89 e5             	mov    %rsp,%rbp
  7c:	b8 95 48 89 ca       	mov    $0xca894895,%eax
  81:	5d                   	pop    %rbp
  82:	c3                   	retq   

0000000000000083 <setval_449>:
  83:	55                   	push   %rbp
  84:	48 89 e5             	mov    %rsp,%rbp
  87:	48 89 7d f8          	mov    %rdi,-0x8(%rbp)
  8b:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
  8f:	c7 00 48 09 ce 90    	movl   $0x90ce0948,(%rax)
  95:	90                   	nop
  96:	5d                   	pop    %rbp
  97:	c3                   	retq   

0000000000000098 <getval_324>:
  98:	55                   	push   %rbp
  99:	48 89 e5             	mov    %rsp,%rbp
  9c:	b8 48 89 de 90       	mov    $0x90de8948,%eax
  a1:	5d                   	pop    %rbp
  a2:	c3                   	retq   

00000000000000a3 <setval_109>:
  a3:	55                   	push   %rbp
  a4:	48 89 e5             	mov    %rsp,%rbp
  a7:	48 89 7d f8          	mov    %rdi,-0x8(%rbp)
  ab:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
  af:	c7 00 48 89 c1 c1    	movl   $0xc1c18948,(%rax)
  b5:	90                   	nop
  b6:	5d                   	pop    %rbp
  b7:	c3                   	retq   

00000000000000b8 <setval_148>:
  b8:	55                   	push   %rbp
  b9:	48 89 e5             	mov    %rsp,%rbp
  bc:	48 89 7d f8          	mov    %rdi,-0x8(%rbp)
  c0:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
  c4:	c7 00 48 a9 c3 90    	movl   $0x90c3a948,(%rax)
  ca:	90                   	nop
  cb:	5d                   	pop    %rbp
  cc:	c3                   	retq   

00000000000000cd <setval_430>:
  cd:	55                   	push   %rbp
  ce:	48 89 e5             	mov    %rsp,%rbp
  d1:	48 89 7d f8          	mov    %rdi,-0x8(%rbp)
  d5:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
  d9:	c7 00 48 89 d7 92    	movl   $0x92d78948,(%rax)
  df:	90                   	nop
  e0:	5d                   	pop    %rbp
  e1:	c3                   	retq   

00000000000000e2 <getval_357>:
  e2:	55                   	push   %rbp
  e3:	48 89 e5             	mov    %rsp,%rbp
  e6:	b8 48 01 ce c2       	mov    $0xc2ce0148,%eax
  eb:	5d                   	pop    %rbp
  ec:	c3                   	retq   

00000000000000ed <getval_216>:
  ed:	55                   	push   %rbp
  ee:	48 89 e5             	mov    %rsp,%rbp
  f1:	b8 48 89 de 92       	mov    $0x92de8948,%eax
  f6:	5d                   	pop    %rbp
  f7:	c3                   	retq   

00000000000000f8 <setval_175>:
  f8:	55                   	push   %rbp
  f9:	48 89 e5             	mov    %rsp,%rbp
  fc:	48 89 7d f8          	mov    %rdi,-0x8(%rbp)
 100:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
 104:	c7 00 48 a9 c1 c3    	movl   $0xc3c1a948,(%rax)
 10a:	90                   	nop
 10b:	5d                   	pop    %rbp
 10c:	c3                   	retq   

000000000000010d <addval_427>:
 10d:	55                   	push   %rbp
 10e:	48 89 e5             	mov    %rsp,%rbp
 111:	89 7d fc             	mov    %edi,-0x4(%rbp)
 114:	8b 45 fc             	mov    -0x4(%rbp),%eax
 117:	2d b8 fe 31 6f       	sub    $0x6f31feb8,%eax
 11c:	5d                   	pop    %rbp
 11d:	c3                   	retq   

000000000000011e <setval_356>:
 11e:	55                   	push   %rbp
 11f:	48 89 e5             	mov    %rsp,%rbp
 122:	48 89 7d f8          	mov    %rdi,-0x8(%rbp)
 126:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
 12a:	c7 00 f2 48 89 ca    	movl   $0xca8948f2,(%rax)
 130:	90                   	nop
 131:	5d                   	pop    %rbp
 132:	c3                   	retq   

0000000000000133 <addval_310>:
 133:	55                   	push   %rbp
 134:	48 89 e5             	mov    %rsp,%rbp
 137:	89 7d fc             	mov    %edi,-0x4(%rbp)
 13a:	8b 45 fc             	mov    -0x4(%rbp),%eax
 13d:	2d b8 76 3c 6b       	sub    $0x6b3c76b8,%eax
 142:	5d                   	pop    %rbp
 143:	c3                   	retq   

0000000000000144 <addval_481>:
 144:	55                   	push   %rbp
 145:	48 89 e5             	mov    %rsp,%rbp
 148:	89 7d fc             	mov    %edi,-0x4(%rbp)
 14b:	8b 45 fc             	mov    -0x4(%rbp),%eax
 14e:	2d b8 76 21 38       	sub    $0x382176b8,%eax
 153:	5d                   	pop    %rbp
 154:	c3                   	retq   

0000000000000155 <addval_258>:
 155:	55                   	push   %rbp
 156:	48 89 e5             	mov    %rsp,%rbp
 159:	89 7d fc             	mov    %edi,-0x4(%rbp)
 15c:	8b 45 fc             	mov    -0x4(%rbp),%eax
 15f:	2d b8 72 35 3c       	sub    $0x3c3572b8,%eax
 164:	5d                   	pop    %rbp
 165:	c3                   	retq   

0000000000000166 <setval_187>:
 166:	55                   	push   %rbp
 167:	48 89 e5             	mov    %rsp,%rbp
 16a:	48 89 7d f8          	mov    %rdi,-0x8(%rbp)
 16e:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
 172:	c7 00 4e 48 8b d7    	movl   $0xd78b484e,(%rax)
 178:	90                   	nop
 179:	5d                   	pop    %rbp
 17a:	c3                   	retq   

000000000000017b <addval_235>:
 17b:	55                   	push   %rbp
 17c:	48 89 e5             	mov    %rsp,%rbp
 17f:	89 7d fc             	mov    %edi,-0x4(%rbp)
 182:	8b 45 fc             	mov    -0x4(%rbp),%eax
 185:	2d b8 76 28 3c       	sub    $0x3c2876b8,%eax
 18a:	5d                   	pop    %rbp
 18b:	c3                   	retq   

000000000000018c <addval_395>:
 18c:	55                   	push   %rbp
 18d:	48 89 e5             	mov    %rsp,%rbp
 190:	89 7d fc             	mov    %edi,-0x4(%rbp)
 193:	8b 45 fc             	mov    -0x4(%rbp),%eax
 196:	2d a8 6f 6f 3c       	sub    $0x3c6f6fa8,%eax
 19b:	5d                   	pop    %rbp
 19c:	c3                   	retq   

000000000000019d <addval_312>:
 19d:	55                   	push   %rbp
 19e:	48 89 e5             	mov    %rsp,%rbp
 1a1:	89 7d fc             	mov    %edi,-0x4(%rbp)
 1a4:	8b 45 fc             	mov    -0x4(%rbp),%eax
 1a7:	2d a8 6f 6f 3c       	sub    $0x3c6f6fa8,%eax
 1ac:	5d                   	pop    %rbp
 1ad:	c3                   	retq   

00000000000001ae <getval_122>:
 1ae:	55                   	push   %rbp
 1af:	48 89 e5             	mov    %rsp,%rbp
 1b2:	b8 48 89 c3 90       	mov    $0x90c38948,%eax
 1b7:	5d                   	pop    %rbp
 1b8:	c3                   	retq   

00000000000001b9 <addval_121>:
 1b9:	55                   	push   %rbp
 1ba:	48 89 e5             	mov    %rsp,%rbp
 1bd:	89 7d fc             	mov    %edi,-0x4(%rbp)
 1c0:	8b 45 fc             	mov    -0x4(%rbp),%eax
 1c3:	2d b8 fe 31 6f       	sub    $0x6f31feb8,%eax
 1c8:	5d                   	pop    %rbp
 1c9:	c3                   	retq   

00000000000001ca <setval_245>:
 1ca:	55                   	push   %rbp
 1cb:	48 89 e5             	mov    %rsp,%rbp
 1ce:	48 89 7d f8          	mov    %rdi,-0x8(%rbp)
 1d2:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
 1d6:	c7 00 48 89 ca c7    	movl   $0xc7ca8948,(%rax)
 1dc:	90                   	nop
 1dd:	5d                   	pop    %rbp
 1de:	c3                   	retq   

00000000000001df <end_farm>:
 1df:	55                   	push   %rbp
 1e0:	48 89 e5             	mov    %rsp,%rbp
 1e3:	b8 01 00 00 00       	mov    $0x1,%eax
 1e8:	5d                   	pop    %rbp
 1e9:	c3                   	retq   
