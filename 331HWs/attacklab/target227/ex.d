
ex.o:     file format elf64-x86-64


Disassembly of section .text:

0000000000000000 <.text>:
   0:	48 c7 c6 76 0f 40 58 	mov    $0x58400f76,%rsi
   7:	48 c7 c7 76 0f 40 58 	mov    $0x58400f76,%rdi
   e:	48 d1 e8             	shr    %rax
  11:	48 d1 ea             	shr    %rdx
  14:	48 d1 ec             	shr    %rsp
  17:	c3                   	retq   
