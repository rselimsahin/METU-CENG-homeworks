/********************************************************
 * Kernels to be optimized for the CS:APP Performance Lab
 ********************************************************/

#include <stdio.h>
#include <stdlib.h>
#include "defs.h"
/* 
 * Please fill in the following student struct 
 */
team_t team = {
    "2171999",              /* Student ID */

    "Ramazan Selim SAHIN",     /* full name */
    "e2171999@metu.edu.tr",  /* email address */

    "",                   /* leave blank */
    ""                    /* leave blank */
};


/***************
 * CONVOLUTION KERNEL
 ***************/

/******************************************************
 * Your different versions of the convolution functions  go here
 ******************************************************/

/* 
 * naive_conv - The naive baseline version of convolution 
 */
char naive_conv_descr[] = "naive_conv: Naive baseline implementation";
void naive_conv(int dim,int *src, int *ker,int *dst) {
    int i,j,k,l;

    for(i = 0; i < dim-8+1; i++)
        for(j = 0; j < dim-8+1; j++) {
            dst[j*dim+i] = 0;
            for(k = 0; k < 8; k++)
                for(l = 0; l < 8; l++) {
                    dst[j*dim+i] = dst[j*dim+i] +src[(j+l)*dim+(i+k)]*ker[l*dim+k];
                }
        }
            
        
}

/* 
 * convolution - Your current working version of convolution
 * IMPORTANT: This is the version you will be graded on
 */
char convolution_descr[] = "Dot product: Current working version";
void convolution(int dim,int *src, int *ker,int *dst) {
    
    int trans[64];
    int *p_trans=&trans[0];

    *p_trans++=ker[0];
    *p_trans++=ker[1];
    *p_trans++=ker[2];
    *p_trans++=ker[3];
    *p_trans++=ker[4];
    *p_trans++=ker[5];
    *p_trans++=ker[6];
    *p_trans++=ker[7];
    ker+=dim;

    *p_trans++=ker[0];
    *p_trans++=ker[1];
    *p_trans++=ker[2];
    *p_trans++=ker[3];
    *p_trans++=ker[4];
    *p_trans++=ker[5];
    *p_trans++=ker[6];
    *p_trans++=ker[7];
    ker+=dim;
    
    *p_trans++=ker[0];
    *p_trans++=ker[1];
    *p_trans++=ker[2];
    *p_trans++=ker[3];
    *p_trans++=ker[4];
    *p_trans++=ker[5];
    *p_trans++=ker[6];
    *p_trans++=ker[7];
    ker+=dim;
    
    *p_trans++=ker[0];
    *p_trans++=ker[1];
    *p_trans++=ker[2];
    *p_trans++=ker[3];
    *p_trans++=ker[4];
    *p_trans++=ker[5];
    *p_trans++=ker[6];
    *p_trans++=ker[7];
    ker+=dim;
    
    *p_trans++=ker[0];
    *p_trans++=ker[1];
    *p_trans++=ker[2];
    *p_trans++=ker[3];
    *p_trans++=ker[4];
    *p_trans++=ker[5];
    *p_trans++=ker[6];
    *p_trans++=ker[7];
    ker+=dim;
    
    *p_trans++=ker[0];
    *p_trans++=ker[1];
    *p_trans++=ker[2];
    *p_trans++=ker[3];
    *p_trans++=ker[4];
    *p_trans++=ker[5];
    *p_trans++=ker[6];
    *p_trans++=ker[7];
    ker+=dim;
    
    *p_trans++=ker[0];
    *p_trans++=ker[1];
    *p_trans++=ker[2];
    *p_trans++=ker[3];
    *p_trans++=ker[4];
    *p_trans++=ker[5];
    *p_trans++=ker[6];
    *p_trans++=ker[7];
    ker+=dim;
    
    *p_trans++=ker[0];
    *p_trans++=ker[1];
    *p_trans++=ker[2];
    *p_trans++=ker[3];
    *p_trans++=ker[4];
    *p_trans++=ker[5];
    *p_trans++=ker[6];
    *p_trans=ker[7];
    ker+=dim;
    
    int range, range1, range2, val1, val2;
    int *ind_src, *ind_dst, *ind_ind_src;
    range=dim-7;
    range1=range;
    
    while(range1>0){
        range2=range;
        ind_src=src;
        ind_dst=dst;
        
        while(range2>0){
            ind_ind_src=ind_src;
            p_trans=&trans[0];
            val1=ind_ind_src[0]**p_trans++;
            val2=ind_ind_src[1]**p_trans++;
            val1+=ind_ind_src[2]**p_trans++;
            val2+=ind_ind_src[3]**p_trans++;
            val1+=ind_ind_src[4]**p_trans++;
            val2+=ind_ind_src[5]**p_trans++;
            val1+=ind_ind_src[6]**p_trans++;
            val2+=ind_ind_src[7]**p_trans++;
            ind_ind_src+=dim;
            
            val1+=ind_ind_src[0]**p_trans++;
            val2+=ind_ind_src[1]**p_trans++;
            val1+=ind_ind_src[2]**p_trans++;
            val2+=ind_ind_src[3]**p_trans++;
            val1+=ind_ind_src[4]**p_trans++;
            val2+=ind_ind_src[5]**p_trans++;
            val1+=ind_ind_src[6]**p_trans++;
            val2+=ind_ind_src[7]**p_trans++;
            ind_ind_src+=dim;
            
            val1+=ind_ind_src[0]**p_trans++;
            val2+=ind_ind_src[1]**p_trans++;
            val1+=ind_ind_src[2]**p_trans++;
            val2+=ind_ind_src[3]**p_trans++;
            val1+=ind_ind_src[4]**p_trans++;
            val2+=ind_ind_src[5]**p_trans++;
            val1+=ind_ind_src[6]**p_trans++;
            val2+=ind_ind_src[7]**p_trans++;
            ind_ind_src+=dim;
            
            val1+=ind_ind_src[0]**p_trans++;
            val2+=ind_ind_src[1]**p_trans++;
            val1+=ind_ind_src[2]**p_trans++;
            val2+=ind_ind_src[3]**p_trans++;
            val1+=ind_ind_src[4]**p_trans++;
            val2+=ind_ind_src[5]**p_trans++;
            val1+=ind_ind_src[6]**p_trans++;
            val2+=ind_ind_src[7]**p_trans++;
            ind_ind_src+=dim;
            
            val1+=ind_ind_src[0]**p_trans++;
            val2+=ind_ind_src[1]**p_trans++;
            val1+=ind_ind_src[2]**p_trans++;
            val2+=ind_ind_src[3]**p_trans++;
            val1+=ind_ind_src[4]**p_trans++;
            val2+=ind_ind_src[5]**p_trans++;
            val1+=ind_ind_src[6]**p_trans++;
            val2+=ind_ind_src[7]**p_trans++;
            ind_ind_src+=dim;
            
            val1+=ind_ind_src[0]**p_trans++;
            val2+=ind_ind_src[1]**p_trans++;
            val1+=ind_ind_src[2]**p_trans++;
            val2+=ind_ind_src[3]**p_trans++;
            val1+=ind_ind_src[4]**p_trans++;
            val2+=ind_ind_src[5]**p_trans++;
            val1+=ind_ind_src[6]**p_trans++;
            val2+=ind_ind_src[7]**p_trans++;
            ind_ind_src+=dim;
            
            val1+=ind_ind_src[0]**p_trans++;
            val2+=ind_ind_src[1]**p_trans++;
            val1+=ind_ind_src[2]**p_trans++;
            val2+=ind_ind_src[3]**p_trans++;
            val1+=ind_ind_src[4]**p_trans++;
            val2+=ind_ind_src[5]**p_trans++;
            val1+=ind_ind_src[6]**p_trans++;
            val2+=ind_ind_src[7]**p_trans++;
            ind_ind_src+=dim;
            
            val1+=ind_ind_src[0]**p_trans++;
            val2+=ind_ind_src[1]**p_trans++;
            val1+=ind_ind_src[2]**p_trans++;
            val2+=ind_ind_src[3]**p_trans++;
            val1+=ind_ind_src[4]**p_trans++;
            val2+=ind_ind_src[5]**p_trans++;
            val1+=ind_ind_src[6]**p_trans++;
            val2+=ind_ind_src[7]**p_trans;

            ind_src++;
            val1+=val2;
            *ind_dst++ =val1;
            range2--;
        }
        dst+=dim;
        src+=dim;
        range1--;
    }
}

/********************************** ***********************************
 * register_conv_functions - Register all of your different versions
 *     of the convolution functions  with the driver by calling the
 *     add_conv_function() for each test function. When you run the
 *     driver program, it will test and report the performance of each
 *     registered test function.  
 *********************************************************************/

void register_conv_functions() {
    add_conv_function(&naive_conv, naive_conv_descr);   
    add_conv_function(&convolution, convolution_descr);   
    /* ... Register additional test functions here */
}




/***************
 * MATRIX MULTIP KERNEL
 ***************/

/******************************************************
 * Your different versions of the matrix multiplications  go here
 ******************************************************/

/* 
 * naive_matrix_multiplication - The naive baseline version of matrix multiplication 
 */
char naive_matrix_multiplication_descr[] = "Naive_matrix_multiplication: Naive baseline implementation";
void naive_matrix_multiplication(int dim,int *src, int *src2,int *dst) {
    int i,j,k;

    for(i = 0; i < dim; i++)
        for(j = 0; j < dim; j++) {
            dst[j*dim+i]=0;
            for(k = 0; k < dim; k++) 
                dst[j*dim+i] = dst[j*dim+i] + src[j*dim+k]*src2[i+k*dim];
        }    
}


/* 
 * matrix_multiplication - Your current working version of matrix_multiplication
 * IMPORTANT: This is the version you will be graded on
 */

void swap(int *x1, int *x2){
    //int temp=*x1;
    //*x1=*x2;
    //*x2=temp;
    *x1=*x1^*x2;
    *x2=*x1^*x2;
    *x1=*x2^*x1;
}
char matrix_multiplication_descr[] = "Matrix multiplications: Current working version";
void matrix_multiplication(int dim,int *src, int *src2,int *dst) 
{
    int i,j,k,ix;
    int src_1,src_2,src_3,src_4;
    int src2_1_1,src2_1_2,src2_1_3,src2_1_4;
    int src2_2_1,src2_2_2,src2_2_3,src2_2_4;
    int src2_3_1,src2_3_2,src2_3_3,src2_3_4;
    int src2_4_1,src2_4_2,src2_4_3,src2_4_4;
    for (i=0; i<dim; i++) {
        for (j=i+1; j<dim; j++) {
            swap(&src2[i*dim+j], &src2[j*dim+i]);
        }
    }


    for(i=0;i<dim;i+=4){
        for(j=0;j<dim;j+=4){
                dst[(i)*dim+(j)]=0;
                dst[(i)*dim+(j+1)]=0;
                dst[(i)*dim+(j+2)]=0;
                dst[(i)*dim+(j+3)]=0; 
                dst[(i+1)*dim+(j)]=0;
                dst[(i+1)*dim+(j+1)]=0;
                dst[(i+1)*dim+(j+2)]=0;
                dst[(i+1)*dim+(j+3)]=0;
                dst[(i+2)*dim+(j)]=0;
                dst[(i+2)*dim+(j+1)]=0;
                dst[(i+2)*dim+(j+2)]=0;
                dst[(i+2)*dim+(j+3)]=0;
                dst[(i+3)*dim+(j)]=0;
                dst[(i+3)*dim+(j+1)]=0;
                dst[(i+3)*dim+(j+2)]=0;
                dst[(i+3)*dim+(j+3)]=0;
            for(k=0;k<dim;k+=4){
                ix=i;
                src_1=src[(ix)*dim+(k)];
                src_2=src[(ix)*dim+(k+1)];
                src_3=src[(ix)*dim+(k+2)];
                src_4=src[(ix)*dim+(k+3)];

                src2_1_1=src2[(j)*dim+(k)];
                src2_1_2=src2[(j)*dim+(k+1)];
                src2_1_3=src2[(j)*dim+(k+2)];
                src2_1_4=src2[(j)*dim+(k+3)];

                src2_2_1=src2[(j+1)*dim+(k)];
                src2_2_2=src2[(j+1)*dim+(k+1)];
                src2_2_3=src2[(j+1)*dim+(k+2)];
                src2_2_4=src2[(j+1)*dim+(k+3)];

                src2_3_1=src2[(j+2)*dim+(k)];
                src2_3_2=src2[(j+2)*dim+(k+1)];
                src2_3_3=src2[(j+2)*dim+(k+2)];
                src2_3_4=src2[(j+2)*dim+(k+3)];

                src2_4_1=src2[(j+3)*dim+(k)];
                src2_4_2=src2[(j+3)*dim+(k+1)];
                src2_4_3=src2[(j+3)*dim+(k+2)];
                src2_4_4=src2[(j+3)*dim+(k+3)];

                dst[(ix)*dim+(j)]+=src_1*src2_1_1
                                 +src_2*src2_1_2
                                 +src_3*src2_1_3
                                 +src_4*src2_1_4;

                dst[(ix)*dim+(j+1)]+=src_1*src2_2_1
                                   +src_2*src2_2_2
                                   +src_3*src2_2_3
                                   +src_4*src2_2_4;

                dst[(ix)*dim+(j+2)]+=src_1*src2_3_1
                                   +src_2*src2_3_2
                                   +src_3*src2_3_3
                                   +src_4*src2_3_4;

                dst[(ix)*dim+(j+3)]+=src_1*src2_4_1
                                   +src_2*src2_4_2
                                   +src_3*src2_4_3
                                   +src_4*src2_4_4;
                ix++;
                src_1=src[(ix)*dim+(k)];
                src_2=src[(ix)*dim+(k+1)];
                src_3=src[(ix)*dim+(k+2)];
                src_4=src[(ix)*dim+(k+3)];


                dst[(ix)*dim+(j)]+=src_1*src2_1_1
                                 +src_2*src2_1_2
                                 +src_3*src2_1_3
                                 +src_4*src2_1_4;

                dst[(ix)*dim+(j+1)]+=src_1*src2_2_1
                                   +src_2*src2_2_2
                                   +src_3*src2_2_3
                                   +src_4*src2_2_4;

                dst[(ix)*dim+(j+2)]+=src_1*src2_3_1
                                   +src_2*src2_3_2
                                   +src_3*src2_3_3
                                   +src_4*src2_3_4;

                dst[(ix)*dim+(j+3)]+=src_1*src2_4_1
                                   +src_2*src2_4_2
                                   +src_3*src2_4_3
                                   +src_4*src2_4_4;
                ix++;
                src_1=src[(ix)*dim+(k)];
                src_2=src[(ix)*dim+(k+1)];
                src_3=src[(ix)*dim+(k+2)];
                src_4=src[(ix)*dim+(k+3)];


                dst[(ix)*dim+(j)]+=src_1*src2_1_1
                                 +src_2*src2_1_2
                                 +src_3*src2_1_3
                                 +src_4*src2_1_4;

                dst[(ix)*dim+(j+1)]+=src_1*src2_2_1
                                   +src_2*src2_2_2
                                   +src_3*src2_2_3
                                   +src_4*src2_2_4;

                dst[(ix)*dim+(j+2)]+=src_1*src2_3_1
                                   +src_2*src2_3_2
                                   +src_3*src2_3_3
                                   +src_4*src2_3_4;

                dst[(ix)*dim+(j+3)]+=src_1*src2_4_1
                                   +src_2*src2_4_2
                                   +src_3*src2_4_3
                                   +src_4*src2_4_4;
                ix++;
                src_1=src[(ix)*dim+(k)];
                src_2=src[(ix)*dim+(k+1)];
                src_3=src[(ix)*dim+(k+2)];
                src_4=src[(ix)*dim+(k+3)];


                dst[(ix)*dim+(j)]+=src_1*src2_1_1
                                 +src_2*src2_1_2
                                 +src_3*src2_1_3
                                 +src_4*src2_1_4;

                dst[(ix)*dim+(j+1)]+=src_1*src2_2_1
                                   +src_2*src2_2_2
                                   +src_3*src2_2_3
                                   +src_4*src2_2_4;

                dst[(ix)*dim+(j+2)]+=src_1*src2_3_1
                                   +src_2*src2_3_2
                                   +src_3*src2_3_3
                                   +src_4*src2_3_4;

                dst[(ix)*dim+(j+3)]+=src_1*src2_4_1
                                   +src_2*src2_4_2
                                   +src_3*src2_4_3
                                   +src_4*src2_4_4;
            }
        }
    }

    for (i=0; i<dim; i++) {
        for (j=i+1; j<dim; j++) {
            swap(&src2[i*dim+j], &src2[j*dim+i]);
        }
    }

}

/*********************************************************************
 * register_matrix_multiplication_functions - Register all of your different versions
 *     of the matrix multiplication  with the driver by calling the
 *     add_matrix_multiplication_function() for each test function. When you run the
 *     driver program, it will test and report the performance of each
 *     registered test function.  
 *********************************************************************/

void register_matrix_multiplication_functions() {
    add_matrix_multiplication_function(&naive_matrix_multiplication, naive_matrix_multiplication_descr);   
    add_matrix_multiplication_function(&matrix_multiplication, matrix_multiplication_descr);   
    /* ... Register additional test functions here */
}

